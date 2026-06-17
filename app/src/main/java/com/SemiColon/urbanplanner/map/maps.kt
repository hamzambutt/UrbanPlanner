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
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Place
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.viewmodel.compose.viewModel
import com.SemiColon.urbanplanner.analysis.AmenitiesViewModel
import com.SemiColon.urbanplanner.analysis.HazardsViewModel
import com.SemiColon.urbanplanner.analysis.RadarChart
import com.SemiColon.urbanplanner.analysis.SolarViewModel
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
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
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.annotations.PolygonOptions
import org.maplibre.android.geometry.LatLng as MapLibreLatLng

enum class AnalysisMode { AMENITIES, SOLAR, HAZARDS }

// Updated Data Class to include Icons
data class MapStyle(
    val name: String,
    val url: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsScreen(
    amenitiesViewModel: AmenitiesViewModel = viewModel(),
    solarViewModel: SolarViewModel = viewModel(),
    hazardsViewModel: HazardsViewModel = viewModel()
) {
    val context = LocalContext.current

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
    
    var analysisMode by remember { mutableStateOf(AnalysisMode.AMENITIES) }

    // Track what the user wants to do
    var selectedLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var showConfigSheet by remember { mutableStateOf(false) }
    
    val personas by amenitiesViewModel.personas.collectAsState()
    val selectedPersona by amenitiesViewModel.selectedPersona.collectAsState()

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

    // Debounced search via LaunchedEffect
    LaunchedEffect(searchQuery) {
        if (searchQuery.length > 2) {
            delay(500)
            // Push the network call to the background IO thread!
            searchResults = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                GeocodingService.searchPlace(searchQuery)
            }
        } else {
            searchResults = emptyList()
        }
    }

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
        // Map is ready, setup long click
        mapInstance?.addOnMapLongClickListener { point ->
            // Drop a pin
            mapInstance?.clear() // Clear old markers
            mapInstance?.addMarker(MarkerOptions().position(point).title("Selected Location"))
            
            android.util.Log.d("URBAN_DEBUG", "Pin dropped! Opening configuration sheet...")
            
            selectedLocation = Pair(point.latitude, point.longitude)
            showConfigSheet = true
            true
        }
    }

    val analysisResult by amenitiesViewModel.analysisResult.collectAsState()
    val isAnalyzingAmenities by amenitiesViewModel.isLoading.collectAsState()
    
    val solarResult by solarViewModel.solarResult.collectAsState()
    val isAnalyzingSolar by solarViewModel.isLoading.collectAsState()
    
    val hazardsData by hazardsViewModel.hazardsData.collectAsState()
    val isAnalyzingHazards by hazardsViewModel.isLoading.collectAsState()
    
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(analysisMode) {
        if (analysisMode == AnalysisMode.HAZARDS) {
            hazardsViewModel.fetchData()
        }
    }

    val errorMessage by amenitiesViewModel.errorMessage.collectAsState()

    LaunchedEffect(analysisResult, errorMessage, isAnalyzingAmenities, solarResult, isAnalyzingSolar) {
        if (analysisMode != AnalysisMode.HAZARDS && (isAnalyzingAmenities || analysisResult != null || errorMessage != null || isAnalyzingSolar || solarResult != null)) {
            showBottomSheet = true
        }
    }
    
    // Watch for solarResult to draw heatmap
    LaunchedEffect(solarResult) {
        if (solarResult != null) {
            val grid = solarResult!!.heatmapGrid
            grid.forEach { cell ->
                val cellLat = cell["latitude"]?.jsonPrimitive?.doubleOrNull ?: 0.0
                val cellLon = cell["longitude"]?.jsonPrimitive?.doubleOrNull ?: 0.0
                val score = cell["solar_score"]?.jsonPrimitive?.doubleOrNull ?: 0.0
                if (cellLat != 0.0 && cellLon != 0.0) {
                    val halfSize = 0.0005 // roughly 50m
                    val polygon = PolygonOptions()
                        .add(MapLibreLatLng(cellLat - halfSize, cellLon - halfSize))
                        .add(MapLibreLatLng(cellLat - halfSize, cellLon + halfSize))
                        .add(MapLibreLatLng(cellLat + halfSize, cellLon + halfSize))
                        .add(MapLibreLatLng(cellLat + halfSize, cellLon - halfSize))
                        .add(MapLibreLatLng(cellLat - halfSize, cellLon - halfSize))
                        .fillColor(if (score > 80) android.graphics.Color.parseColor("#88FFCC00") else android.graphics.Color.parseColor("#88FF6600"))
                    
                    mapInstance?.addPolygon(polygon)
                }
            }
        }
    }
    
    // Watch for hazardsData to draw markers
    LaunchedEffect(hazardsData) {
        if (analysisMode == AnalysisMode.HAZARDS && hazardsData != null) {
            val events = hazardsData!!["events"]?.jsonArray
            events?.forEach { event ->
                val title = event.jsonObject["title"]?.jsonPrimitive?.content ?: "Hazard"
                val geometries = event.jsonObject["geometries"]?.jsonArray
                geometries?.forEach { geo ->
                    val coords = geo.jsonObject["coordinates"]?.jsonArray
                    if (coords != null && coords.size >= 2) {
                        val lon = coords[0].jsonPrimitive.doubleOrNull ?: 0.0
                        val lat = coords[1].jsonPrimitive.doubleOrNull ?: 0.0
                        if (lat != 0.0 && lon != 0.0) {
                            mapInstance?.addMarker(
                                MarkerOptions()
                                    .position(MapLibreLatLng(lat, lon))
                                    .title("Hazard: $title")
                            )
                        }
                    }
                }
            }
        }
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
                    
                    // Set default zoomed-in view instantly
                    map.cameraPosition = CameraPosition.Builder()
                        .target(LatLng(33.573, 73.039)) 
                        .zoom(15.0)
                        .build()
                        
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
                    onValueChange = { searchQuery = it },
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

            // Persona Selector moved to Bottom Sheet
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
        
        // 5. BOTTOM SHEET FOR SPATIAL ANALYSIS
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { 
                    showBottomSheet = false 
                    amenitiesViewModel.clearResultsOnly()
                    solarViewModel.clearAnalysis()
                    mapInstance?.clear()
                },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isAnalyzingAmenities || isAnalyzingSolar) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Analyzing area...", style = MaterialTheme.typography.bodyLarge)
                    } else if (analysisMode == AnalysisMode.AMENITIES && analysisResult != null) {
                        val result = analysisResult!!
                        Text(
                            text = "Livability Score: ${result.overallScore ?: 0}/100",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Radar Chart
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(16.dp)
                        ) {
                            RadarChart(data = result.radarChartData ?: emptyMap())
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Amenities Found: ${result.amenitiesFound?.size ?: 0}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(max = 300.dp)) {
                            items(result.amenitiesFound ?: emptyList()) { amenity ->
                                ListItem(
                                    headlineContent = { Text(amenity.name?.ifBlank { "Unknown ${amenity.type?.replace("_", " ")?.capitalize()}" } ?: "Unknown") },
                                    supportingContent = { Text("${(amenity.distance ?: 0.0).toInt()} meters away") },
                                    leadingContent = { 
                                        val icon = when (amenity.category) {
                                            "healthcare" -> Icons.Default.LocalHospital
                                            "education" -> Icons.Default.School
                                            "park", "recreation" -> Icons.Default.Park
                                            "food", "restaurant" -> Icons.Default.Restaurant
                                            "commercial", "shop" -> Icons.Default.ShoppingCart
                                            else -> Icons.Default.Place
                                        }
                                        Icon(icon, contentDescription = amenity.category, tint = MaterialTheme.colorScheme.primary)
                                    }
                                )
                                HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha=0.1f))
                            }
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                    } else if (analysisMode == AnalysisMode.AMENITIES && amenitiesViewModel.errorMessage.collectAsState().value != null) {
                        Text(
                            text = "Analysis Failed",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = amenitiesViewModel.errorMessage.collectAsState().value ?: "Unknown Error",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                    } else if (analysisMode == AnalysisMode.SOLAR && solarResult != null) {
                        val result = solarResult!!
                        val annualKwh = result.analysis["annual_kwh_produced"]?.jsonPrimitive?.doubleOrNull ?: 0.0
                        val savingsUsd = result.analysis["financial_savings_usd"]?.jsonPrimitive?.doubleOrNull ?: 0.0
                        
                        Text(
                            text = "Solar Potential",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Annual Output: ${annualKwh} kWh",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Estimated Savings: $${savingsUsd} / year",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                    } else if (analysisMode == AnalysisMode.HAZARDS) {
                        Text(
                            text = "Hazards & Compliance",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (isAnalyzingHazards) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Fetching active hazards and societies...")
                        } else {
                            val activeHazardsCount = hazardsData?.get("events")?.jsonArray?.size ?: 0
                            Text(
                                text = "Active NASA Hazards: $activeHazardsCount",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.height(32.dp))
                        }
                    }
                }
            }
        }
        // 6. ANALYSIS CONFIGURATION SHEET
        if (showConfigSheet) {
            AnalysisConfigurationSheet(
                personas = personas,
                selectedMode = analysisMode,
                onModeChange = { analysisMode = it },
                selectedPersona = selectedPersona,
                onPersonaChange = { amenitiesViewModel.selectPersona(it) },
                onRunAnalysis = {
                    showConfigSheet = false
                    selectedLocation?.let { loc ->
                        if (analysisMode == AnalysisMode.AMENITIES) {
                            selectedPersona?.let { persona ->
                                amenitiesViewModel.selectPersona(persona)
                                amenitiesViewModel.analyzeLocation(loc.first, loc.second)
                            }
                        } else if (analysisMode == AnalysisMode.SOLAR) {
                            solarViewModel.analyzeLocation(loc.first, loc.second)
                        } else if (analysisMode == AnalysisMode.HAZARDS) {
                            hazardsViewModel.fetchData()
                        }
                    }
                },
                onDismiss = { showConfigSheet = false }
            )
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
            locationComponent.zoomWhileTracking(15.0)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalysisConfigurationSheet(
    personas: List<com.SemiColon.urbanplanner.network.models.Persona>,
    selectedMode: AnalysisMode,
    onModeChange: (AnalysisMode) -> Unit,
    selectedPersona: com.SemiColon.urbanplanner.network.models.Persona?,
    onPersonaChange: (com.SemiColon.urbanplanner.network.models.Persona) -> Unit,
    onRunAnalysis: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Configure Analysis",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            // 1. Select Analysis Type (Amenities, Solar, Hazards)
            Text("Analysis Type", style = MaterialTheme.typography.labelLarge)
            androidx.compose.foundation.lazy.LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(AnalysisMode.values()) { mode ->
                    FilterChip(
                        selected = selectedMode == mode,
                        onClick = { onModeChange(mode) },
                        label = { Text(mode.name.lowercase().replaceFirstChar { it.uppercase() }) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Select Persona (Only show if Amenities is selected!)
            if (selectedMode == AnalysisMode.AMENITIES) {
                Text("Select Persona", style = MaterialTheme.typography.labelLarge)
                if (personas.isEmpty()) {
                    Text(
                        text = "Loading personas...",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                } else {
                    androidx.compose.foundation.lazy.LazyRow(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(personas) { persona ->
                            FilterChip(
                                selected = selectedPersona == persona,
                                onClick = { onPersonaChange(persona) },
                                label = { Text(persona.name) }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            } else {
                // Add some spacing if persona row is hidden
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 3. The Run Button
            Button(
                onClick = onRunAnalysis,
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedMode != AnalysisMode.AMENITIES || selectedPersona != null
            ) {
                Text("Run Analysis")
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}