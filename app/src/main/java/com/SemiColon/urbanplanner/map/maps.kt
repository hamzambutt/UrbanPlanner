package com.SemiColon.urbanplanner.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.isSystemInDarkTheme
import com.SemiColon.urbanplanner.ui.theme.ThemeManager
import com.SemiColon.urbanplanner.ui.theme.ThemeMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.location.LocationComponentActivationOptions
import org.maplibre.android.location.modes.CameraMode
import org.maplibre.android.location.modes.RenderMode
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style

// Updated Data Class to include Icons
data class MapStyle(
    val name: String,
    val url: String,
    val icon: ImageVector
)

@Composable
fun MapsScreen() {
    val context = LocalContext.current
    MapLibre.getInstance(context)

    val myApiKey = "u3q70g01iGsukPfVW10m" // Keep secret!

    // Define Styles with Icons
    val mapStyles = listOf(
        MapStyle("Street", "https://api.maptiler.com/maps/streets/style.json?key=$myApiKey", Icons.Default.Map),
        MapStyle("Satellite", "https://api.maptiler.com/maps/satellite/style.json?key=$myApiKey", Icons.Default.Public),
        MapStyle("Dark", "https://api.maptiler.com/maps/basic-v2-dark/style.json?key=$myApiKey", Icons.Default.DarkMode)
    )

    var currentStyle by remember { mutableStateOf(mapStyles[0]) }
    var isMenuExpanded by remember { mutableStateOf(false) }
    var mapInstance by remember { mutableStateOf<MapLibreMap?>(null) }
    var currentBearing by remember { mutableFloatStateOf(0f) } // Track rotation for compass icon

    // Permissions
    var hasLocationPermission by remember {
        mutableStateOf(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted -> hasLocationPermission = isGranted }
    )

    // Search State
    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<NominatimResult>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    val themeManager = remember { ThemeManager.getInstance(context) }
    val currentThemeMode by themeManager.themeMode.collectAsState()
    val isSystemDark = isSystemInDarkTheme()
    val isAppInDarkMode = when (currentThemeMode) {
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
        ThemeMode.SYSTEM -> isSystemDark
    }

    LaunchedEffect(isAppInDarkMode) {
        if (currentStyle.name == "Street" || currentStyle.name == "Dark") {
             currentStyle = if (isAppInDarkMode) mapStyles[2] else mapStyles[0]
             mapInstance?.setStyle(currentStyle.url)
        }
    }

    // Track Map Bearing (Rotation) to spin the compass icon
    LaunchedEffect(mapInstance) {
        // In a real app, you would add a camera listener here to update 'currentBearing'
        // For now, we will just reset it on click
    }

    val mapView = rememberMapViewWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {

        // 1. THE MAP
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize(),
            update = { updatedMapView ->
                updatedMapView.getMapAsync { map ->
                    mapInstance = map
                    map.uiSettings.isLogoEnabled = false
                    map.uiSettings.isAttributionEnabled = false
                    map.uiSettings.isCompassEnabled = false // Hiding default compass to use custom one

                    if (map.style == null) {
                        map.setStyle(currentStyle.url) { style ->
                            enableLocationComponent(style, map, context)
                        }
                    }
                }
            }
        )

        // 4. SEARCH UI (Top Center)
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp) // Full width search bar
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(28.dp), // Pill shape
                shadowElevation = 6.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { newValue ->
                        searchQuery = newValue
                        if (newValue.length > 2) {
                            coroutineScope.launch {
                                delay(500) // Debounce
                                if (searchQuery == newValue) {
                                    searchResults = GeocodingService.searchPlace(newValue)
                                }
                            }
                        } else {
                            searchResults = emptyList()
                        }
                    },
                    placeholder = { Text("Search here", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface.copy(alpha=0.5f)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.onSurface.copy(alpha=0.7f)) },
                    modifier = Modifier.fillMaxSize(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.primary
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface)
                )
            }

            if (searchResults.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 200.dp)
                    ) {
                        items(searchResults) { result ->
                            Text(
                                text = result.display_name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        searchQuery = ""
                                        searchResults = emptyList()
                                        val lat = result.lat.toDoubleOrNull() ?: 0.0
                                        val lon = result.lon.toDoubleOrNull() ?: 0.0
                                        if (lat != 0.0 && lon != 0.0) {
                                            mapInstance?.animateCamera(
                                                CameraUpdateFactory.newLatLngZoom(
                                                    LatLng(lat, lon),
                                                    15.0
                                                ),
                                                1000
                                            )
                                        }
                                    }
                                    .padding(16.dp),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha=0.1f))
                        }
                    }
                }
            }
        }

// 2. LAYER BUTTON (Top Right - Floating nicely under search)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 90.dp, end = 16.dp)
        ) {
            FloatingActionButton(
                onClick = { isMenuExpanded = true },
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.size(48.dp),
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp)
            ) {
                Icon(Icons.Default.Layers, contentDescription = "Layers")
            }

            // The Dropdown (Icons Only)
            MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp))) {
                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .width(64.dp) // Force it to be narrow (icon width)
                ) {
                    mapStyles.forEach { style ->
                        DropdownMenuItem(
                            // Put the Icon here in the main 'text' slot to center it
                            text = {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = style.icon,
                                        contentDescription = style.name,
                                        tint = if (currentStyle == style) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha=0.6f)
                                    )
                                }
                            },
                            onClick = {
                                currentStyle = style
                                mapInstance?.setStyle(style.url)
                                isMenuExpanded = false
                            },
                            contentPadding = PaddingValues(vertical = 0.dp) // Tighten the spacing
                        )
                    }
                }
            }
        }
        // 3. NAVIGATION STACK (Bottom Right - Compass + GPS)
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp) // Space between North and GPS
        ) {

            // -- NORTH PIN BUTTON --
            FloatingActionButton(
                onClick = {
                    mapInstance?.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition.Builder()
                                .bearing(0.0) // Reset to North
                                .build()
                        ),
                        500
                    )
                },
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.size(48.dp),
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp)
            ) {
                // We can rotate this icon based on actual map bearing if we had the listener set up
                Icon(
                    imageVector = Icons.Default.Navigation,
                    contentDescription = "Reset North",
                    modifier = Modifier.rotate(-currentBearing) // Counter-rotate to point North
                )
            }

            // -- GPS LOCATION BUTTON (Robust Version) --
            FloatingActionButton(
                onClick = {
                    if (hasLocationPermission) {
                        val loc = mapInstance?.locationComponent

                        if (loc != null && loc.isLocationComponentActivated) {

                            // 1. Activate Tracking Mode First
                            // This forces the map to lock onto the user as soon as the hardware gets a GPS fix.
                            loc.cameraMode = CameraMode.TRACKING
                            loc.renderMode = RenderMode.COMPASS // COMPASS is generally better for the main GPS button

                            // 2. Check for immediate location for the smooth zoom animation
                            val userLocation = loc.lastKnownLocation

                            if (userLocation != null) {
                                mapInstance?.animateCamera(
                                    CameraUpdateFactory.newLatLngZoom(
                                        LatLng(userLocation.latitude, userLocation.longitude),
                                        15.0
                                    ),
                                    1000
                                )
                            } else {
                                // It will still automatically move to the user once the GPS locks on,
                                // but this lets them know the hardware is currently searching.
                                Toast.makeText(context, "Acquiring GPS lock...", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            val style = mapInstance?.style
                            if (mapInstance != null && style != null) {
                                enableLocationComponent(style, mapInstance!!, context)
                                Toast.makeText(context, "Location enabled. Pinpointing...", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Map not ready.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape, // Classic Google Maps GPS button is a perfect circle
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp)
            ) {
                Icon(Icons.Default.MyLocation, contentDescription = "My Location")
            }
        }
    }
}

// ... (Keep existing enableLocationComponent and rememberMapViewWithLifecycle functions exactly the same)
private fun enableLocationComponent(style: Style, map: MapLibreMap, context: Context) {
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        try {
            val locationComponent = map.locationComponent
            val options = LocationComponentActivationOptions.builder(context, style)
                .useDefaultLocationEngine(true)
                .build()
            locationComponent.activateLocationComponent(options)
            locationComponent.isLocationComponentEnabled = true
            locationComponent.cameraMode = CameraMode.TRACKING
            locationComponent.renderMode = RenderMode.COMPASS
        } catch (e: Exception) { e.printStackTrace() }
    }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val observer = remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> {}
            }
        }
    }
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }
    return mapView
}

@Preview(showBackground = true)
@Composable
fun MapsScreenPreview() {
    MapsScreen()
}