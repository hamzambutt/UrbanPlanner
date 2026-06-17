# UrbanPlanner - 6-Phase Implementation Plan

This document outlines the roadmap for implementing the backend integration, AI Agent interface, and map visualization layers for UrbanPlanner.

## User Review Required

> [!IMPORTANT]
> Please review this implementation plan before we proceed. The API schema definition (`api.json`) was mentioned in Phase 1 but I couldn't find it in the repository. Please provide the file or its contents so I can correctly map the JSON schemas to Kotlin data classes.

## Open Questions

> [!WARNING]
> **API Schema Missing:** Where is the `api.json` file located, or could you provide the schema definitions for Auth, Agent, Analysis, Hazards, and Change Detection endpoints? I need this to accurately create the data models in Phase 1.

## Proposed Changes

We will tackle the implementation in 6 distinct phases.

---

### Phase 1: Establish the Network Layer & Data Models
Map the JSON schemas to Kotlin data classes and configure a secure Ktor routing setup.

#### [NEW] `com/SemiColon/urbanplanner/network/models/AuthModels.kt`
- Define `LoginRequest`, `AuthResponse` and other auth-related serializable models.

#### [NEW] `com/SemiColon/urbanplanner/network/models/AgentModels.kt`
- Define `ChatRequest` and `ChatResponse` for the conversational agent.

#### [NEW] `com/SemiColon/urbanplanner/network/models/AnalysisModels.kt`
- Define `AnalysisRequest`, `SolarRequest` (with a default `system_size_kw` of 6.2), and `SolarResponse`.

#### [NEW] `com/SemiColon/urbanplanner/network/TokenManager.kt`
- Create a class to securely fetch the current access token from the Supabase client state.

#### [MODIFY] `com/SemiColon/urbanplanner/network/ApiClient.kt` (or similar new setup)
- Extend the Ktor client setup. Use Ktor's `Auth` plugin with a Bearer token provider tied to `TokenManager`, intercepting all requests except auth routes.

---

### Phase 2: AI Agent & Chat Integration
Provide the conversational interface.

#### [NEW] `com/SemiColon/urbanplanner/agent/AgentRepository.kt`
- Implement `sendMessage(query: String, sessionId: String?)`.
- Implement `clearSession(sessionId: String)`.

#### [NEW] `com/SemiColon/urbanplanner/agent/AgentViewModel.kt`
- Hold `MutableStateFlow<List<ChatMessage>>` for UI.
- Store the active `session_id`.

#### [NEW] `com/SemiColon/urbanplanner/agent/ChatScreen.kt`
- Build a `LazyColumn` for chat history.
- Include a sticky bottom text field for user input.
- Parse `map_data` in responses to trigger MapLibre camera movements.

---

### Phase 3: Spatial Analysis (Amenities & Personas)
Score locations based on user preferences.

#### [NEW] `com/SemiColon/urbanplanner/analysis/AmenitiesRepository.kt`
- Implement `getPersonas()` for preset lists.
- Implement `analyzeLocation(lat, lon, preferences)` calling `/api/v1/amenities/analyze`.

#### [NEW] `com/SemiColon/urbanplanner/analysis/AmenitiesViewModel.kt`
- Manage selected persona and loading states.

#### [MODIFY] `com/SemiColon/urbanplanner/map/maps.kt` (or related Map UI)
- Implement a long-press listener on MapLibre to capture lat/lon and drop a temporary pin.
- Add a bottom sheet showing `overall_score`.
- Use a Compose charting library (e.g., Vico) or Canvas to render the `radar_chart_data`.

---

### Phase 4: Solar Potential & Windrose
Visual and data-heavy map layers.

#### [MODIFY] Network API Services
- Add Ktor endpoints for `/api/v1/solar/analyze` and `/api/v1/windrose/analyze`.

#### [MODIFY] Map Overlays
- **Solar Heatmap:** Parse `heatmap_grid` (JSON array) into a MapLibre `GeoJsonSource` of polygons. Apply a `FillLayer` with color interpolations.
- **Windrose:** Parse radial matrix data into a specialized Compose Canvas graphic sitting in an overlay card rather than directly on the map.

---

### Phase 5: Hazards & Compliance Layers
Static and active geospatial data rendering.

#### [MODIFY] Network API Services
- Add data fetching methods for `/api/v1/hazards/active` and `/api/v1/compliance/illegal-societies`.

#### [NEW] Map Filters UI
- Build a "Map Layers" toggle menu (floating buttons on the screen).

#### [MODIFY] Dynamic Map Rendering
- **Illegal Societies:** Inject coordinate boundaries into a `LineLayer` (red borders) on the map when toggled.
- **Active Hazards:** Place specific `SymbolLayer` icons (e.g., fire, flood) based on NASA EONET data when toggled.

---

### Phase 6: Temporal Change Detection
Multipart image uploads for change detection.

#### [MODIFY] Network API Services
- Configure Ktor to handle `MultiPartFormDataContent` to upload two GeoTIFFs to `/api/v1/change-detection/detect`.
- Setup downloading/polling mechanism for `/api/v1/change-detection/download/{job_id}`.

#### [NEW] `com/SemiColon/urbanplanner/changedetection/ChangeDetectionScreen.kt`
- Add UI using Compose's `rememberLauncherForActivityResult` with `ActivityResultContracts.GetContent()` to pick GeoTIFF files.
- Handle upload and result display (binary mask GeoTIFF).

## Verification Plan

### Automated Tests
- Validate Kotlin data classes correctly serialize/deserialize mocked JSON responses.
- Verify Ktor client intercepts requests and attaches the Bearer token correctly.

### Manual Verification
- Test MapLibre interactions: Camera bounds movement for chat `map_data`, and long-press pin dropping.
- Ensure Heatmaps and LineLayers toggle properly and render in correct coordinates.
- Test multipart form uploads with sample images.
