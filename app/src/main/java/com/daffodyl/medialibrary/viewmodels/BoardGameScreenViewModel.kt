package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.models.BoardGame
import com.daffodyl.medialibrary.repositories.BoardGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BoardGameScreenViewModel(
    private val boardGamesRepository: BoardGamesRepository,
    private val boardGameId: Long
): ViewModel() {
    private val _boardGame = MutableStateFlow<BoardGame?>(null)
    val boardGame: StateFlow<BoardGame?> = _boardGame

    fun deleteBoardGame() {
        viewModelScope.launch(Dispatchers.Default) {
            boardGamesRepository.deleteBoardGame(boardGameId)
        }
    }

    init {
        viewModelScope.launch {
            boardGamesRepository.boardGames.collect { boardGames ->
                _boardGame.value = boardGames.find { it.id.toLong() == boardGameId }
            }
        }
    }

    companion object {
        val BOARD_GAME_ID_KEY = object: CreationExtras.Key<Long> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val boardGameId = this[BOARD_GAME_ID_KEY] as Long
                BoardGameScreenViewModel(
                    application.boardGamesRepository,
                    boardGameId
                )
            }
        }
    }
}