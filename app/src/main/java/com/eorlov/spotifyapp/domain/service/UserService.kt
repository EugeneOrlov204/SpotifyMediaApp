package com.eorlov.spotifyapp.domain.service

import com.eorlov.spotifyapp.data.model.SearchResult
import com.eorlov.spotifyapp.presentation.model.User
import retrofit2.Response

interface UserService {
    suspend fun getUser(accessToken: String): User
    suspend fun search(accessToken: String, q: String, type: String): SearchResult
}