package com.eorlov.spotifyapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)