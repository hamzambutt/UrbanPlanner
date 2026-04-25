# Map Search via OSM API (Nominatim) Implementation Plan

Based on a review of the codebase, here is an analysis of your current mapping implementation and what you need to add to achieve a functional map search using the OpenStreetMap (OSM) API.

## What You Are Currently Doing Next

You have set up a solid foundation for mapping using modern Android practices:
- **Map Renderer:** You are using `MapLibre GL` (`org.maplibre.gl:android-sdk:12.3.0`) integrated gracefully into Compose using `AndroidView` and `LifecycleEventObserver`.
- **Map Styles:** You've integrated `MapTiler` to provide beautiful vector map styles (Street, Satellite, Dark) using a secret API key.
- **Location Capabilities:** Your `MapsScreen` successfully handles runtime location permissions (`ACCESS_FINE_LOCATION`), camera tracking, rendering the user's location via the default compass, and rotating back to North.
- **Networking/Serialization Setup:** You've prepared the networking stack perfectly in `build.gradle.kts`. You have Ktor client core/android (`2.3.8`) and `kotlinx-serialization-json` installed, which are the exact tools needed to ping the OSM API.

## What is Missing to Make Map Search Work

To implement the OpenStreetMap Search (Nominatim API), you need the following key missing components:

### 1. Data Models for OSM Response [NEW]
You need Kotlin data classes to parse the JSON returned by OpenStreetMap.
Nominatim returns an array of places, each possessing variables like `lat`, `lon`, and `display_name`.
Since you have `kotlinx-serialization`, you can create models like:
```kotlin
@Serializable
data class NominatimResult(
    val place_id: Long,
    val lat: String,
    val lon: String,
    val display_name: String
)
```

### 2. Geocoding Service Client [NEW]
You need a class (or a suspend function) built around `Ktor` to query the Nominatim API (`https://nominatim.openstreetmap.org/search?q={query}&format=json`).
> [!WARNING]
> **OSM Usage Policy:** Nominatim requires you to set a valid `User-Agent` HTTP header identifying your app (e.g. `User-Agent: UrbanPlannerApp/1.0`). Failure to do so may result in your requests being blocked.

### 3. Search UI Overlay in `MapsScreen.kt` [MODIFY]
You need to add a Search Bar at the top of the map.
- A `TextField` or `SearchBar` component overlaying the map.
- A `LazyColumn` dropdown that displays the autocomplete/search results dynamically.
- State variables handling the search text `var searchQuery by remember { mutableStateOf("") }` and search results.

### 4. Search Selection & Map Camera Update [MODIFY]
When the user taps on a search result from the list:
- Convert the string `lat` and `lon` to Doubles.
- Clear/collapse the search results.
- Execute `mapInstance?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lon), 14.0), 1000)` to fly the user to the searched location.
- Optionally add a pin/marker to that specific location on the map.

## Proposed Changes

### App/API Layer
#### [NEW] `NominatimResult.kt`
- Define the serializable data structure for the coordinates and display name.
#### [NEW] `GeocodingService.kt`
- Initialize a Ktor `HttpClient` with a content-negotiation plugin for JSON.
- Expose a `suspend fun searchPlace(query: String): List<NominatimResult>` function.

### UI Layer
#### [MODIFY] `maps.kt`
- Add a Compose `SearchBar` component at the top center of the `Box`.
- Implement a coroutine to debounce text inputs and fetch results from `GeocodingService` dynamically.
- Implement the onClick handler inside the search list to pan the `MapLibre` camera.

## Open Questions

> [!IMPORTANT]
> 1. **Autocomplete Behavior:** Do you want the search to autocomplete as you type (requires debouncing requests), or only search when the user presses an explicit "Search" button on the keyboard?
> 2. **Markers:** When a user searches for a location and the map pans to it, do you want to place a visual marker (pin) at that exact coordinate? If so, we need to add a MapLibre symbol layer or annotation for the pin.

## Verification Plan

### Manual Verification
- Compile and run the app.
- Go to `MapsScreen`.
- Type "New York" in the newly added search bar.
- Wait for the results to populate in the dropdown.
- Select "New York, USA" from the dropdown list.
- Ensure the map camera cleanly animates directly to New York.
