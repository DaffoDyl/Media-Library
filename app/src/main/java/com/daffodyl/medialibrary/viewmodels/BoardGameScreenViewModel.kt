package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.repositories.BoardGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BoardGameScreenViewModel(
    private val boardGameId: Int?,
    private val boardGamesRepository: BoardGamesRepository
): ViewModel() {
    private val _title = MutableStateFlow("")
    private val _minPlayers = MutableStateFlow(0)
    private val _maxPlayers = MutableStateFlow(0)
    private val _genre = MutableStateFlow("")
    private val _notes = MutableStateFlow("")

    val title: StateFlow<String> = _title
    val minPlayers: StateFlow<Int> = _minPlayers
    val maxPlayers: StateFlow<Int> = _maxPlayers
    val genre: StateFlow<String> = _genre
    val notes: StateFlow<String> = _notes

    fun deleteBoardGame() {
        if (boardGameId != null) {
            viewModelScope.launch(Dispatchers.Default) {
                boardGamesRepository.deleteBoardGame(boardGameId)
            }
        }
    }

    init {
        if (boardGameId != null) {
            viewModelScope.launch(Dispatchers.Default) {
                boardGamesRepository.boardGames.collect { boardGames ->
                    val boardGame = boardGames.find { it.id == boardGameId }
                    if (boardGame != null) {
                        _title.value = boardGame.title
                        _minPlayers.value = boardGame.minPlayers
                        _maxPlayers.value = boardGame.maxPlayers
                        _genre.value = boardGame.genre
                        _notes.value = boardGame.notes
                    }
                }
            }
        }
    }

    companion object {
        var BOARD_GAME_ID_KEY = object : CreationExtras.Key<Int?> {}

        val Factory = viewModelFactory {
            initializer {
                val boardGameId = this[BOARD_GAME_ID_KEY]
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                BoardGameScreenViewModel(
                    boardGameId,
                    application.boardGamesRepository
                )
            }
        }
    }
}