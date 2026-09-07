package com.SemiColon.urbanplanner.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.SemiColon.urbanplanner.network.models.SolarResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject

class SolarViewModel : ViewModel() {
    private val repository = SolarRepository()

    private val _systemSizeKw = MutableStateFlow(6.2)
    val systemSizeKw: StateFlow<Double> = _systemSizeKw.asStateFlow()

    private val _solarResult = MutableStateFlow<SolarResponse?>(null)
    val solarResult: StateFlow<SolarResponse?> = _solarResult.asStateFlow()

    private val _windroseResult = MutableStateFlow<JsonObject?>(null)
    val windroseResult: StateFlow<JsonObject?> = _windroseResult.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun updateSystemSize(size: Double) {
        _systemSizeKw.value = size
    }

    fun analyzeLocation(lat: Double, lon: Double) {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            _isLoading.value = true
            try {
                _solarResult.value = repository.analyzeSolar(lat, lon, _systemSizeKw.value)
                _windroseResult.value = repository.getWindrose(lat, lon)
            } catch (e: Exception) {
                android.util.Log.e("URBAN_DEBUG", "Solar Network crashed: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearAnalysis() {
        _solarResult.value = null
        _windroseResult.value = null
    }
}
