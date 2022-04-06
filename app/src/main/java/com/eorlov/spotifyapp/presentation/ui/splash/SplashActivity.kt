package com.eorlov.spotifyapp.presentation.ui.splash

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eorlov.spotifyapp.R
import com.eorlov.spotifyapp.domain.utils.Status
import com.eorlov.spotifyapp.presentation.ui.main.MainActivity
import com.eorlov.spotifyapp.presentation.utils.Constants.REQUEST_CODE
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        authenticateSpotify()
    }

    private fun authenticateSpotify() {
        val request = splashViewModel.getAuthorizationRequest()
        AuthorizationClient.openLoginInBrowser(this, request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response: AuthorizationResponse =
                AuthorizationClient.getResponse(resultCode, intent)
            processResponse(response)
        }
    }

    private fun waitForUserInfo(accessToken: String) = runBlocking {
        val user = splashViewModel.getUser(accessToken)

        splashViewModel.saveUserId(user?.id ?: return@runBlocking)
        startMainActivity()
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri: Uri? = intent?.data
        uri?.let {
            val response = AuthorizationResponse.fromUri(it)
            processResponse(response)
        }
    }

    private fun processResponse(response: AuthorizationResponse) {
        when (response.type) {
            AuthorizationResponse.Type.TOKEN -> {
                splashViewModel.saveToken(response.accessToken)
                waitForUserInfo(response.accessToken)
            }
            AuthorizationResponse.Type.ERROR -> {
                Log.d(TAG, "Error is ${response.error}")
            }
            else -> {
                Log.d(TAG, "Else is ${response.type}")
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val TAG = "SplashActivity"
    }
}