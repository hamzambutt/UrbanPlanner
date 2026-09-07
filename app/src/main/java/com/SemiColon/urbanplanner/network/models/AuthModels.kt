package com.SemiColon.urbanplanner.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class SignupRequest(
    val email: String,
    val password: String,
    @SerialName("full_name") val fullName: String,
    val role: String = "b2c",
    @SerialName("organization_name") val organizationName: String? = null
)

@Serializable
data class RefreshRequest(
    @SerialName("refresh_token") val refreshToken: String
)

@Serializable
data class AuthResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String,
    val user: JsonObject
)

@Serializable
data class ProfileResponse(
    val id: String,
    @SerialName("full_name") val fullName: String?,
    @SerialName("organization_name") val organizationName: String?,
    val role: String,
    val status: String
)

@Serializable
data class ValidationError(
    val loc: List<String>,
    val msg: String,
    val type: String
)

@Serializable
data class HTTPValidationError(
    val detail: List<ValidationError>
)
