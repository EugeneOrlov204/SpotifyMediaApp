package com.eorlov.spotifyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    val tracks: Tracks? = null
)