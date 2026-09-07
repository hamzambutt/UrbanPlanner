package com.SemiColon.urbanplanner.analysis

import com.SemiColon.urbanplanner.network.ApiClient
import com.SemiColon.urbanplanner.network.models.AnalysisRequest
import com.SemiColon.urbanplanner.network.models.AnalysisResponse
import com.SemiColon.urbanplanner.network.models.Persona
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AmenitiesRepository {

    suspend fun getPersonas(): List<Persona> {
        return try {
            val response = ApiClient.client.get("${ApiClient.BASE_URL}/api/v1/amenities/personas")
            response.body<List<Persona>>()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun analyzeLocation(lat: Double, lon: Double, preferences: Map<String, Int>): AnalysisResponse? {
        return try {
            val request = AnalysisRequest(
                latitude = lat,
                longitude = lon,
                preferences = preferences
            )
            val response = ApiClient.client.post("${ApiClient.BASE_URL}/api/v1/amenities/analyze") {
                setBody(request)
            }
            response.body<AnalysisResponse>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
