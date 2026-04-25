package com.SemiColon.urbanplanner.map

import kotlinx.serialization.Serializable

@Serializable
data class NominatimResult(
    val place_id: Long,
    val lat: String,
    val lon: String,
    val display_name: String
)
