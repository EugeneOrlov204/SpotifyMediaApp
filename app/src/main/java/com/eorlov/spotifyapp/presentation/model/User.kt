package com.eorlov.spotifyapp.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var birthdate: String? = null,
    var country: String? = null,
    var display_name: String? = null,
    var email: String? = null,
    var id: String? = null
)