package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.repositories.VideoGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoGameScreenViewModel(
    private val videoGameId: Long?,
    private val videoGamesRepository: VideoGamesRepository
): ViewModel() {
    private val _title = MutableStateFlow("")
    private val _developer = MutableStateFlow("")
    private val _genre = MutableStateFlow("")
    private val _rating = MutableStateFlow("")
    private val _platform = MutableStateFlow("")
    private val _notes = MutableStateFlow("")

    val title: StateFlow<String> = _title
    val developer: StateFlow<String> = _developer
    val rating: StateFlow<String> = _rating
    val platform: StateFlow<String> = _platform
    val genre: StateFlow<String> = _genre
    val notes: StateFlow<String> = _notes

    fun deleteVideoGame() {
        if (videoGameId != null) {
            viewModelScope.launch(Dispatchers.Default) {
                videoGamesRepository.deleteVideoGame(videoGameId)
            }
        }
    }

    init {
        if (videoGameId != null) {
            viewModelScope.launch(Dispatchers.Default) {
                videoGamesRepository.videoGames.collect { videoGames ->
                    val videoGame = videoGames.find { it.id == videoGameId }
                    if (videoGame != null) {
                        _title.value = videoGame.title
                        _developer.value = videoGame.developer
                        _genre.value = videoGame.genre
                        _rating.value = videoGame.rating
                        _platform.value = videoGame.platform
                        _notes.value = videoGame.notes
                    }
                }
            }
        }
    }

    companion object {
        var BOARD_GAME_ID_KEY = object : CreationExtras.Key<Long?> {}

        val Factory = viewModelFactory {
            initializer {
                val videoGameId = this[BOARD_GAME_ID_KEY]
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                VideoGameScreenViewModel(
                    videoGameId,
                    application.videoGamesRepository
                )
            }
        }
    }
}