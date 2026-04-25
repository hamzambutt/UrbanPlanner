package com.SemiColon.urbanplanner.map

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object GeocodingService {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun searchPlace(query: String): List<NominatimResult> {
        return try {
            val response = client.get("https://nominatim.openstreetmap.org/search") {
                url {
                    parameters.append("q", query)
                    parameters.append("format", "json")
                    parameters.append("limit", "5")
                }
                header("User-Agent", "UrbanPlannerApp/1.0")
            }
            response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
