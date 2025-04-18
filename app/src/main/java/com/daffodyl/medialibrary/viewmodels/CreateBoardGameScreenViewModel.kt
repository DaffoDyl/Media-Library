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

class CreateBoardGameScreenViewModel(
    private val boardGameId: Long?,
    private val boardGamesRepository: BoardGamesRepository
): ViewModel() {
    private val _title = MutableStateFlow("")
    private val _minPlayers = MutableStateFlow(0L)
    private val _maxPlayers = MutableStateFlow(0L)
    private val _genre = MutableStateFlow("")
    private val _notes = MutableStateFlow("")

    val title: StateFlow<String> = _title
    val minPlayers: StateFlow<Long> = _minPlayers
    val maxPlayers: StateFlow<Long> = _maxPlayers
    val genre: StateFlow<String> = _genre
    val notes: StateFlow<String> = _notes

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setMinPlayers(minPlayers: Long) {
        _minPlayers.value = minPlayers
    }

    fun setMaxPlayers(maxPlayers: Long) {
        _maxPlayers.value = maxPlayers
    }

    fun setGenre(genre: String) {
        _genre.value = genre
    }

    fun setNotes(notes: String) {
        _notes.value = notes
    }

    fun saveBoardGame() {
        viewModelScope.launch(Dispatchers.Default) {
            if (boardGameId != null) {
                boardGamesRepository.updateBoardGame(
                    boardGameId,
                    _title.value,
                    _minPlayers.value,
                    _maxPlayers.value,
                    _genre.value,
                    _notes.value)
            } else {
                boardGamesRepository.addBoardGames(
                    _title.value,
                    _minPlayers.value,
                    _maxPlayers.value,
                    _genre.value,
                    _notes.value)
            }
        }
    }

    init {
        if (boardGameId != null) {
            viewModelScope.launch {
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
        var BOARD_GAME_ID_KEY = object : CreationExtras.Key<Long?> {}

        val Factory = viewModelFactory {
            initializer {
                val boardGameId = this[BOARD_GAME_ID_KEY]
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                CreateBoardGameScreenViewModel(
                    boardGameId,
                    application.boardGamesRepository
                )
            }
        }
    }
}