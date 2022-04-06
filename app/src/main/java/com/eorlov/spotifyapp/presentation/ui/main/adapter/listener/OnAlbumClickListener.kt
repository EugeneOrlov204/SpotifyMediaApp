package com.eorlov.spotifyapp.presentation.ui.main.adapter.listener

import com.eorlov.spotifyapp.data.model.Album

interface OnAlbumClickListener {
    fun playAlbum(album: Album)
}