package com.SemiColon.urbanplanner.analysis

import com.SemiColon.urbanplanner.network.ApiClient
import com.SemiColon.urbanplanner.network.models.SolarRequest
import com.SemiColon.urbanplanner.network.models.SolarResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.json.JsonObject

class SolarRepository {

    suspend fun analyzeSolar(lat: Double, lon: Double, systemSizeKw: Double): SolarResponse? {
        return try {
            val request = SolarRequest(
                latitude = lat,
                longitude = lon,
                systemSizeKw = systemSizeKw
            )
            val response = ApiClient.client.post("${ApiClient.BASE_URL}/api/v1/solar/analyze") {
                setBody(request)
            }
            response.body<SolarResponse>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getWindrose(lat: Double, lon: Double): JsonObject? {
        return try {
            val response = ApiClient.client.get("${ApiClient.BASE_URL}/api/v1/windrose/analyze") {
                parameter("lat", lat)
                parameter("lon", lon)
            }
            response.body<JsonObject>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
