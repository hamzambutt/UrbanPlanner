package com.SemiColon.urbanplanner.agent

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessageEntity(
    @SerialName("id") val id: String? = null,
    @SerialName("user_id") val userId: String,
    @SerialName("text") val text: String,
    @SerialName("is_user") val isUser: Boolean,
    @SerialName("created_at") val createdAt: String? = null
)
