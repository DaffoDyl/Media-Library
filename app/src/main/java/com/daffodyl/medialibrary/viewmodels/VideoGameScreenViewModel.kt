package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.models.VideoGame
import com.daffodyl.medialibrary.repositories.VideoGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoGameScreenViewModel(
    private val videoGamesRepository: VideoGamesRepository,
    private val videoGameId: Long
): ViewModel() {
    private val _videoGame = MutableStateFlow<VideoGame?>(null)
    val videoGame: StateFlow<VideoGame?> = _videoGame

    fun deleteVideoGame() {
        viewModelScope.launch {
            videoGamesRepository.deleteVideoGame(_videoGame.value)
        }
    }

    init {
        viewModelScope.launch {
            videoGamesRepository.videoGames.collect { videoGames ->
                _videoGame.value = videoGames.find { it.id == videoGameId }
            }
        }
    }

    companion object {
        val VIDEO_GAME_ID_KEY = object: CreationExtras.Key<Long?> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val videoGameId = this[VIDEO_GAME_ID_KEY] as Long
                VideoGameScreenViewModel(
                    application.videoGamesRepository,
                    videoGameId
                )
            }
        }
    }
}