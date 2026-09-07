package com.SemiColon.urbanplanner.agent

import kotlinx.serialization.json.JsonObject

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val text: String,
    val isUser: Boolean,
    val mapData: JsonObject? = null,
    val timestamp: Long = System.currentTimeMillis()
)
