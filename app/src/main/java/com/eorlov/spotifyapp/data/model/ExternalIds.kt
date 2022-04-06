package com.eorlov.spotifyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ExternalIds(
    val isrc: String? = null
)