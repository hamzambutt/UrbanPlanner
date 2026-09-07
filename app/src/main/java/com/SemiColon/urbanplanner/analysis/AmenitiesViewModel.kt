package com.SemiColon.urbanplanner.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.SemiColon.urbanplanner.network.models.AnalysisResponse
import com.SemiColon.urbanplanner.network.models.Persona
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AmenitiesViewModel : ViewModel() {
    private val repository = AmenitiesRepository()

    private val _personas = MutableStateFlow<List<Persona>>(emptyList())
    val personas: StateFlow<List<Persona>> = _personas.asStateFlow()

    private val _selectedPersona = MutableStateFlow<Persona?>(null)
    val selectedPersona: StateFlow<Persona?> = _selectedPersona.asStateFlow()

    // Map & Analysis State
    private val _selectedLocation = MutableStateFlow<Pair<Double, Double>?>(null)
    val selectedLocation: StateFlow<Pair<Double, Double>?> = _selectedLocation.asStateFlow()

    private val _analysisResult = MutableStateFlow<AnalysisResponse?>(null)
    val analysisResult: StateFlow<AnalysisResponse?> = _analysisResult.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadPersonas()
    }

    private fun loadPersonas() {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            android.util.Log.d("URBAN_DEBUG", "Attempting to fetch personas from backend...")
            try {
                val fetched = repository.getPersonas()
                
                if (fetched.isEmpty()) {
                    android.util.Log.e("URBAN_DEBUG", "Persona list is empty! Backend unreachable or DB is empty.")
                } else {
                    android.util.Log.d("URBAN_DEBUG", "Successfully loaded ${fetched.size} personas!")
                }
                
                _personas.value = fetched
                if (fetched.isNotEmpty()) {
                    _selectedPersona.value = fetched.first()
                }
            } catch (e: Exception) {
                android.util.Log.e("URBAN_DEBUG", "Failed to fetch personas: ${e.message}", e)
            }
        }
    }

    fun selectPersona(persona: Persona) {
        _selectedPersona.value = persona
        // Removed auto-trigger to allow explicit "Run Analysis" from bottom sheet
    }

    fun onMapLongClick(lat: Double, lon: Double) {
        _selectedLocation.value = Pair(lat, lon)
        analyzeDroppedPin(lat, lon)
    }

    // Keep the old signature for compatibility with maps.kt if needed, or change it
    fun analyzeLocation(lat: Double, lon: Double) {
        onMapLongClick(lat, lon)
    }

    private fun analyzeDroppedPin(lat: Double, lon: Double) {
        val currentPersona = _selectedPersona.value ?: return

        // ADDED Dispatchers.IO HERE!
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            // Also added the "Sending" log so you can track it
            android.util.Log.d("URBAN_DEBUG", "Sending coordinates to backend...")

            _isLoading.value = true
            _errorMessage.value = null

            try {
                val result = repository.analyzeLocation(lat, lon, currentPersona.preferences)
                android.util.Log.d("URBAN_DEBUG", "Received response: $result")

                if (result != null) {
                    _analysisResult.value = result
                } else {
                    _errorMessage.value = "Failed to analyze location. The response might have been invalid or empty."
                }
            } catch (e: Exception) {
                android.util.Log.e("URBAN_DEBUG", "Network crashed: ${e.message}", e)
                _errorMessage.value = "Network error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearResultsOnly() {
        _analysisResult.value = null
        _errorMessage.value = null
    }

    fun clearEntireMap() {
        _analysisResult.value = null
        _errorMessage.value = null
        _selectedLocation.value = null
    }
}
