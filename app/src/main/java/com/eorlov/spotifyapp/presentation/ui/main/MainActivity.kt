package com.eorlov.spotifyapp.presentation.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.eorlov.spotifyapp.data.model.Album
import com.eorlov.spotifyapp.databinding.ActivityMainBinding
import com.eorlov.spotifyapp.presentation.ui.main.adapter.AlbumAdapter
import com.eorlov.spotifyapp.presentation.ui.main.adapter.listener.OnAlbumClickListener
import com.eorlov.spotifyapp.presentation.utils.Constants.CLIENT_ID
import com.eorlov.spotifyapp.presentation.utils.Constants.REDIRECT_URI
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.PlayerState
import com.spotify.protocol.types.Track
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnAlbumClickListener {
    private val mainViewModel: MainViewModel by viewModels()
    private var mSpotifyAppRemote: SpotifyAppRemote? = null
    private lateinit var binding: ActivityMainBinding

    private val albumAdapter: AlbumAdapter by lazy { AlbumAdapter(onAlbumClickListener = this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setListeners()
        initRecycler()
        setObservers()
        authenticateSpotify()
    }

    override fun playAlbum(album: Album) {
        mSpotifyAppRemote?.playerApi?.play(album.uri)
    }

    private fun setObservers() {
        mainViewModel.albumslLiveData.observe(this) { albums ->
            albumAdapter.submitList(albums.toMutableList())
        }
    }

    private fun setListeners() {
        setSearchButtonListener()
    }

    private fun initRecycler() {
        binding.recyclerViewAlbums.apply {
            layoutManager = GridLayoutManager(
                this@MainActivity,
                SPAN_COUNT,
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = albumAdapter
        }
    }

    private fun setSearchButtonListener() {
        binding.buttonSearch.setOnClickListener {
            mainViewModel.search(binding.editTextSearchQuery.text.toString())
        }
    }

    private fun authenticateSpotify() {
        val connectionParams = ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(this, connectionParams,
            object : Connector.ConnectionListener {
                override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                    mSpotifyAppRemote = spotifyAppRemote
                    Log.d("MainActivity", "Connected! Yay!")

                    // Now you can start interacting with App Remote
                    connected()
                }

                override fun onFailure(throwable: Throwable) {
                    Log.e("MyActivity", throwable.message, throwable)

                    // Something went wrong when attempting to connect! Handle errors here
                }
            })

    }

    private fun connected() {
        // Play a playlist
        mSpotifyAppRemote!!.playerApi.play("spotify:album:6hPkbAV3ZXpGZBGUvL6jVM")

        // Subscribe to PlayerState
        mSpotifyAppRemote!!.playerApi
            .subscribeToPlayerState()
            .setEventCallback { playerState: PlayerState ->
                val track: Track? = playerState.track
                if (track != null) {
                    Log.d("MainActivity", track.name.toString() + " by " + track.artist.name)
                }
            }
    }

    companion object {
        private val SPAN_COUNT = 3
    }
}
