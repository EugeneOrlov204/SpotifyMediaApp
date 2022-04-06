package com.eorlov.spotifyapp.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.eorlov.spotifyapp.data.model.Album
import com.eorlov.spotifyapp.databinding.LayoutSpotifyAlbumBinding
import com.eorlov.spotifyapp.presentation.ui.main.adapter.listener.OnAlbumClickListener
import com.eorlov.spotifyapp.presentation.ui.main.adapter.viewHolder.AlbumViewHolder

class AlbumAdapter(
    private val onAlbumClickListener: OnAlbumClickListener
) : ListAdapter<Album, AlbumViewHolder>(object : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean =
        oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutSpotifyAlbumBinding.inflate(layoutInflater)
        return AlbumViewHolder(binding, onAlbumClickListener)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}