package com.SemiColon.urbanplanner.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ChatRequest(
    val query: String,
    @SerialName("session_id") val sessionId: String? = null,
    @SerialName("model_name") val modelName: String = "gemini-2.0-flash"
)

@Serializable
data class ChatResponse(
    val response: String,
    @SerialName("session_id") val sessionId: String,
    @SerialName("map_data") val mapData: JsonObject? = null
)
