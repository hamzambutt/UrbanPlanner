package com.SemiColon.urbanplanner.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class AmenityItem(
    val name: String? = "",
    val lat: Double? = 0.0,
    val lon: Double? = 0.0,
    val distance: Double? = 0.0,
    val category: String? = "unknown",
    val type: String? = "unknown"
)

@Serializable
data class AnalysisRequest(
    val latitude: Double,
    val longitude: Double,
    val radius: Int = 1000,
    val preferences: Map<String, Int>
)

@Serializable
data class AnalysisResponse(
    @SerialName("overall_score") val overallScore: Int? = 0,
    @SerialName("radar_chart_data") val radarChartData: Map<String, Int>? = emptyMap(),
    @SerialName("amenities_found") val amenitiesFound: List<AmenityItem>? = emptyList()
)

@Serializable
data class SolarRequest(
    val latitude: Double,
    val longitude: Double,
    @SerialName("radius_km") val radiusKm: Double? = 0.5,
    @SerialName("step_size_km") val stepSizeKm: Double? = 0.1,
    @SerialName("system_size_kw") val systemSizeKw: Double? = 6.2
)

@Serializable
data class SolarResponse(
    val latitude: Double,
    val longitude: Double,
    val analysis: JsonObject,
    @SerialName("heatmap_grid") val heatmapGrid: List<JsonObject>
)

@Serializable
data class Persona(
    val id: String,
    val name: String,
    val description: String,
    val preferences: Map<String, Int>
)
