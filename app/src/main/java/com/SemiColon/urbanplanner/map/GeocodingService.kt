package com.SemiColon.urbanplanner.map

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
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
                    // Lock the search strictly to Pakistan!
                    parameters.append("countrycodes", "pk") 
                }
                header(io.ktor.http.HttpHeaders.UserAgent, "UrbanPlannerApp/1.0 (hamzamasood420@gmail.com")
            }
            if (response.status.value in 200..299) {
                response.body()
            } else {
                val errorText = response.bodyAsText()
                android.util.Log.e("URBAN_DEBUG", "Geocoding API error ${response.status}: $errorText")
                emptyList()
            }
        } catch (e: kotlinx.coroutines.CancellationException) {
            // Rethrow CancellationException so coroutines can cancel properly!
            throw e
        } catch (e: Exception) {
            android.util.Log.e("URBAN_DEBUG", "Geocoding Search Failed: ${e.message}", e)
            emptyList()
        }
    }
}
