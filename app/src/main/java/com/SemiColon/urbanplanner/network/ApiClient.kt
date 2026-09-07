package com.SemiColon.urbanplanner.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ApiClient {
    

    var BASE_URL = "http://192.168.1.7:8000"

    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        
        install(Auth) {
            bearer {
                loadTokens {
                    val token = TokenManager.getAccessToken()
                    if (token != null) {
                        BearerTokens(token, "")
                    } else {
                        null
                    }
                }
                sendWithoutRequest { request ->
                    // Do not send auth headers for auth routes if they exist
                    !request.url.encodedPath.contains("/auth/")
                }
            }
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
        }
    }
}
