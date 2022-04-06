package com.eorlov.spotifyapp.presentation.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import com.eorlov.spotifyapp.domain.interactor.UserInteractor
import com.eorlov.spotifyapp.domain.storage.Storage
import com.eorlov.spotifyapp.presentation.model.User
import com.eorlov.spotifyapp.presentation.utils.Constants.CLIENT_ID
import com.eorlov.spotifyapp.presentation.utils.Constants.REDIRECT_URI
import com.eorlov.spotifyapp.presentation.utils.Constants.TOKEN_KEY
import com.eorlov.spotifyapp.presentation.utils.Constants.USER_ID_KEY
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val storage: Storage,
    private val userInteractor: UserInteractor
) : ViewModel() {

    fun saveToken(accessToken: String) {
        Log.d(TAG, "GOT AUTH TOKEN: $accessToken")
        storage.save(TOKEN_KEY, accessToken)
    }

    fun getAuthorizationRequest(): AuthorizationRequest {
        val builder: AuthorizationRequest.Builder =
            AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
        builder.setScopes(arrayOf(SCOPE))
        return builder.build()
    }

    fun saveUserId(id: String) {
        Log.d(TAG, "User id is $id")
        storage.save(USER_ID_KEY, id)
    }

    suspend fun getUser(accessToken: String): User? {
       return userInteractor.getUser(accessToken)
    }

    companion object {
        private const val SCOPE = "streaming"
        private const val TAG = "SplashViewModel"
    }
}