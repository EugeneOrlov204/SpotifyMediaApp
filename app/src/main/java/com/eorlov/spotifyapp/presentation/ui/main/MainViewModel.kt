package com.eorlov.spotifyapp.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eorlov.spotifyapp.data.model.Album
import com.eorlov.spotifyapp.domain.interactor.UserInteractor
import com.eorlov.spotifyapp.domain.storage.Storage
import com.eorlov.spotifyapp.presentation.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userInteractor: UserInteractor,
    private val storage: Storage
) : ViewModel() {

    private val _albumsLiveData = MutableLiveData<List<Album>>()
    val albumslLiveData: LiveData<List<Album>>
        get() = _albumsLiveData

    fun search(query: String) {
        viewModelScope.launch {
            val accessToken = storage.getString(Constants.TOKEN_KEY, "") ?: return@launch
            _albumsLiveData.postValue(userInteractor.search(accessToken, query))
        }
    }
}