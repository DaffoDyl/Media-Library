package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.models.VideoGame
import com.daffodyl.medialibrary.repositories.VideoGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoGamesScreenViewModel(
    private val videoGamesRepository: VideoGamesRepository
): ViewModel() {
    private val _videoGames = MutableStateFlow(emptyList<VideoGame>())
    val videoGames: StateFlow<List<VideoGame>> = _videoGames

    init {
        viewModelScope.launch(Dispatchers.Default) {
            videoGamesRepository.videoGames.collect {
                _videoGames.value = it
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                VideoGamesScreenViewModel(
                    application.videoGamesRepository,
                )
            }
        }
    }
}