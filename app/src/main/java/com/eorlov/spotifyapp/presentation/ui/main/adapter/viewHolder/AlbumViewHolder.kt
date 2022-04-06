package com.eorlov.spotifyapp.presentation.ui.main.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.eorlov.spotifyapp.data.model.Album
import com.eorlov.spotifyapp.databinding.LayoutSpotifyAlbumBinding
import com.eorlov.spotifyapp.presentation.ui.main.adapter.listener.OnAlbumClickListener
import com.eorlov.spotifyapp.presentation.utils.ext.loadImage

class AlbumViewHolder(
    private val binding: LayoutSpotifyAlbumBinding,
    private val onAlbumClickListener: OnAlbumClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(album: Album) {
        binding.run {
            imageViewAlbum.loadImage(album.images.firstOrNull()?.url ?: "")
            textViewAlbumName.text = album.name
            textViewArtist.text = album.artists.firstOrNull()?.name ?: ""
        }
        setListeners(album)
    }

    private fun setListeners(album: Album) {
        binding.root.setOnClickListener {
            onAlbumClickListener.playAlbum(album)
        }
    }
}