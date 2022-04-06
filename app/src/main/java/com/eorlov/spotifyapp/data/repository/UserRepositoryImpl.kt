package com.eorlov.spotifyapp.data.repository

import com.eorlov.spotifyapp.data.model.SearchResult
import com.eorlov.spotifyapp.domain.repository.UserRepository
import com.eorlov.spotifyapp.domain.service.UserService
import com.eorlov.spotifyapp.presentation.model.User
import com.eorlov.spotifyapp.presentation.utils.Constants.SEARCH_TYPE
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override suspend fun getUser(accessToken: String): User {
        return userService.getUser("Bearer $accessToken")
    }

    override suspend fun search(accessToken: String, query: String): SearchResult {
        return userService.search("Bearer $accessToken", query, type = SEARCH_TYPE)
    }
}