package com.eorlov.spotifyapp.domain.interactor

import com.eorlov.spotifyapp.data.model.Album
import com.eorlov.spotifyapp.presentation.model.User

interface UserInteractor {
    suspend fun getUser(accessToken: String): User?
    suspend fun search(accessToken: String, query: String) : List<Album>
}