package com.eorlov.spotifyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ArtistX(
    val external_urls: ExternalUrlsXX,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)