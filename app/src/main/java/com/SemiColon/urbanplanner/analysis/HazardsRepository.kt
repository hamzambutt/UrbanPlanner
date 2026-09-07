package com.SemiColon.urbanplanner.analysis

import com.SemiColon.urbanplanner.network.ApiClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.JsonObject

class HazardsRepository {

    suspend fun getActiveHazards(): JsonObject? {
        return try {
            val response = ApiClient.client.get("${ApiClient.BASE_URL}/api/v1/hazards/active")
            response.body<JsonObject>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getIllegalSocieties(): JsonObject? {
        return try {
            val response = ApiClient.client.get("${ApiClient.BASE_URL}/api/v1/compliance/illegal-societies")
            response.body<JsonObject>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
