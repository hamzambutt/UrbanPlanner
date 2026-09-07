# UrbanPlanner - Project Status
*Last Updated: 2026-06-17*

## Overview
UrbanPlanner is a modern Android application built using Kotlin and Jetpack Compose. The app is currently in active development, with foundational architecture, UI components, backend integration, and mapping capabilities already established.

## Tech Stack
- **UI Framework:** Jetpack Compose (Material 3)
- **Language:** Kotlin
- **Networking:** Ktor Client
- **Serialization:** kotlinx.serialization (JSON)
- **Backend/BaaS:** Supabase (Auth and Database)
- **Maps:** MapLibre GL Android SDK, MapTiler, OpenStreetMap (Nominatim API)

## What Has Been Done So Far

### 1. Project Setup & Architecture
- Initialized the Android project using Jetpack Compose.
- Set up Gradle dependencies for Compose, Navigation, Ktor, Supabase, and MapLibre.
- Configured `BuildConfig` for secure integration of backend credentials (Supabase URL and Key).

### 2. User Interface & Theming
- **Theme Manager:** Implemented dynamic theming and color palettes using Material 3 (`Theme.kt`, `Color.kt`, `ThemeManager.kt`).
- **Settings Screen:** Created a settings UI that allows the user to configure app preferences (`SettingsScreen.kt`).
- **Preferences Manager:** Utilized a preferences manager to persist user settings locally (`PreferencesManager.kt`).

### 3. Navigation & Onboarding
- **Splash Screen:** Implemented an initial splash screen (`SplashScreen.kt`) that handles the startup experience.
- **Navigation Graph:** Set up Jetpack Compose Navigation (`Navigation.kt`) to cleanly route between the Splash, Login, Signup, Dashboard, Maps, and Settings screens.

### 4. Authentication (Backend Integration)
- **Supabase Client:** Initialized the Supabase client (`SupabaseClient.kt`) for managing authentication and database interactions.
- **Auth Flow:** Built UI screens for Login and Signup (`Login.kt`, `Signup.kt`).
- **Auth ViewModel:** Created a centralized `AuthViewModel.kt` to handle the authentication state, securely connecting the UI layer to the Supabase backend.

### 5. Maps & Geocoding
- **Map Rendering:** Integrated MapLibre GL for vector map rendering inside Compose via `AndroidView` (`maps.kt`).
- **Map Styles:** Connected MapTiler to provide various rich map styles (Street, Satellite, Dark).
- **Location Services:** Added runtime location permissions (`ACCESS_FINE_LOCATION`) and the ability to track/center the camera on the user's current location.
- **Geocoding Service:** Implemented the OpenStreetMap Nominatim API using Ktor to search for geographic places by name (`GeocodingService.kt`).
- **Data Models:** Created serializable data classes to parse OSM API JSON responses (`NominatimResult.kt`).
- **Map Search UI:** Built an overlay search bar on the map, allowing users to query locations and seamlessly animate the map camera to the selected result.

### 6. Dashboard
- **Dashboard UI:** Developed the main hub screen post-login (`dashboard.kt`) that serves as the entry point to the map and other core app functionalities.

## Next Steps & Recent Updates
- **Maps UI Polish (2026-06-17):** Fixed persona state collection inside `maps.kt` so the persona selector properly defaults and updates. Added a loading fallback for persona fetching and removed a legacy "Switch Mode" floating button.
- **Analysis UI Restructure (2026-06-17):** Implemented a comprehensive `AnalysisConfigurationSheet` in `maps.kt` that cleanly decouples map click events from immediate backend calls. Users can now drop a pin, select an analysis mode (Amenities, Solar, Hazards), pick a Persona, and explicitly run the analysis.
- **ViewModel Architecture & Threading Fixes (2026-06-17):** 
  - Updated `SolarViewModel` and `HazardsViewModel` to safely execute network requests on `Dispatchers.IO` with robust `try/catch/finally` blocks, resolving UI hanging issues.
  - Fixed `AmenitiesViewModel` state management so dismissing the results sheet uses `clearResultsOnly()`, properly preserving the user's map pin location.
  - Implemented lenient data parsing (nullable fields with defaults) in `AnalysisModels.kt` to prevent `SerializationException` crashes on malformed backend responses.
- **Settings & Navigation**: Upgraded the settings UI aesthetics, removed the back arrow from the Change Detection screen, fixed horizontal scrolling for the App Theme selector, and ensured the bottom bar Home button correctly resets the backstack.
- **Networking**: Enabled cleartext traffic (`android:usesCleartextTraffic="true"`) to fix AI chat connection issues to the local backend.
- Polish the mapping UI by potentially adding visual map markers/pins to searched locations.
- Finalize the autocomplete search behavior (e.g., adding debouncing logic to the search bar).
- Continue expanding dashboard and database functionalities as per product requirements.

## Missing Features & Incomplete Modules
During the review of the project's current state, the following incomplete implementations and missing modules were identified:

### 1. Dashboard Placeholder Actions
- **Project History**: The `Project History` quick action button on the dashboard is currently empty and does not navigate to any screen.
- **Analytics**: The `Analytics` quick action button on the dashboard is a placeholder with no underlying UI or logic.

### 2. Analysis Modules (UI and Integration Gaps)
- ~~**Solar, Hazards, and Amenities**: The ViewModels (`SolarViewModel`, `HazardsViewModel`, `AmenitiesViewModel`) and Repositories for these features exist and contain backend networking logic (e.g., fetching illegal societies or personas). However, there is no corresponding user interface or map overlay implementation to visually present these analytical layers to the user.~~ **(Resolved 2026-06-17)** The UI has been fully integrated via the `AnalysisConfigurationSheet` and Bottom Sheets in `maps.kt` which now render radar charts, solar stats, and map markers for hazards.

### 3. Settings Screen Gaps
- **Profile & Security Settings**: The UI buttons exist under the "Account" section, but their `onClick` handlers are empty (`/* Navigate to profile */`). Dedicated screens for editing user profiles or managing security are missing.
- **Notifications**: The `Notifications` settings item under "Preferences" is a visual placeholder and does not toggle any state or navigate to a notification preferences screen.

### 4. AI Chat / Agent Reliability
- **Native Gemini SDK Integration**: Transitioned the `AgentRepository.kt` from making Ktor API calls to a local backend (`/api/v1/agent/chat`) to natively using the Google AI Client for Android (`generativeai:0.9.0`).
- **Secure API Key Management**: API keys are now securely managed via `local.properties` and injected into the app using Gradle `BuildConfig.GEMINI_API_KEY`. This removes the dependency on an external custom backend for AI processing and significantly improves stability and latency.
- **Improved UI Responsiveness**: The Chat feature leverages Kotlin Coroutines (`withContext(Dispatchers.IO)`) to ensure that SDK requests don't block the main UI thread, eliminating micro-stutters.

# AI Agent Module: Architecture Refinements

This document outlines the critical stability and performance upgrades applied to the GeoAI Chat module to ensure production-level reliability.

## 1. Native Gemini SDK Migration
* **The Problem:** The app previously relied on a custom backend to communicate with the AI model. This introduced network overhead, local configuration issues, and potential downtime.
* **The Solution:** Integrated the official `com.google.ai.client.generativeai` SDK natively. `AgentRepository.kt` directly instantiates the `GenerativeModel` with system instructions specific to urban planning, ensuring reliable, low-latency AI responses directly from Google's servers.

## 2. Main-Safe Data Processing
* **The Problem:** Processing AI requests on the default `viewModelScope.launch` dispatcher (the Main UI Thread) caused micro-stutters in the Jetpack Compose UI.
* **The Solution:** Offloaded the network request to `Dispatchers.IO` using `withContext` in `AgentViewModel.kt`, keeping the UI perfectly responsive while waiting for the Gemini model to stream back a response.

## 3. Supabase Cloud Sync & Persistent History
* **The Problem:** Chat sessions were ephemeral and lost upon app restarts or clearing state.
* **The Solution:** Integrated a comprehensive cloud storage architecture using Supabase. The `AgentViewModel` securely synchronizes chat history upon initialization and autonomously persists new user queries and GeoAI responses to a remote `chat_messages` PostgreSQL table, safeguarded by Row Level Security (RLS) policies.

## 4. Premium Material 3 Chat UI/UX
* **The Solution:** Completely redesigned the `ChatScreen` utilizing Material Design 3 guidelines. The interface now boasts a dynamic "Thinking..." animation, conversational bot avatars, inline timestamps, automatic list scrolling (`LazyListState`), and flawless keyboard window resizing (`imePadding()`) to ensure a polished user experience.
