package com.eorlov.spotifyapp.domain.repository

import com.eorlov.spotifyapp.data.model.SearchResult
import com.eorlov.spotifyapp.presentation.model.User

interface UserRepository {
    suspend fun getUser(accessToken: String): User?
    suspend fun search(accessToken: String, query: String): SearchResult?
}