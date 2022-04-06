package com.eorlov.spotifyapp.data.interactor

import android.util.Log
import com.eorlov.spotifyapp.data.model.Album
import com.eorlov.spotifyapp.domain.interactor.UserInteractor
import com.eorlov.spotifyapp.domain.repository.UserRepository
import com.eorlov.spotifyapp.presentation.model.User
import javax.inject.Inject

class UserInteractorImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserInteractor {
    override suspend fun getUser(accessToken: String): User? {
        return userRepository.getUser(accessToken)
    }

    override suspend fun search(accessToken: String, query: String): List<Album> {
        val searchResult = userRepository.search(accessToken, query)
        val albums = mutableListOf<Album>()
        searchResult?.tracks?.items?.forEach {
            Log.d(TAG, "album is ${it.album?.name}, uri is ${it.album?.uri}")
            albums.add(it.album ?: return emptyList())
        }

        return albums
    }

    companion object {
        private const val TAG = "UserInteractorImpl"
    }
}