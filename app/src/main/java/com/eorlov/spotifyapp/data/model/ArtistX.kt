package com.eorlov.spotifyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ArtistX(
    val external_urls: ExternalUrlsXX? = null,
    val href: String? = null,
    val id: String? = null,
    val name: String? = null,
    val type: String? = null,
    val uri: String? = null
)