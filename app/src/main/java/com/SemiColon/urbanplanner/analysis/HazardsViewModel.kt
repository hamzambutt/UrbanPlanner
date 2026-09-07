package com.SemiColon.urbanplanner.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject

class HazardsViewModel : ViewModel() {
    private val repository = HazardsRepository()

    private val _hazardsData = MutableStateFlow<JsonObject?>(null)
    val hazardsData: StateFlow<JsonObject?> = _hazardsData.asStateFlow()

    private val _illegalSocietiesData = MutableStateFlow<JsonObject?>(null)
    val illegalSocietiesData: StateFlow<JsonObject?> = _illegalSocietiesData.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun fetchData() {
        if (_hazardsData.value != null && _illegalSocietiesData.value != null) return
        
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            _isLoading.value = true
            try {
                _hazardsData.value = repository.getActiveHazards()
                _illegalSocietiesData.value = repository.getIllegalSocieties()
            } catch (e: Exception) {
                android.util.Log.e("URBAN_DEBUG", "Hazards Network crashed: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
