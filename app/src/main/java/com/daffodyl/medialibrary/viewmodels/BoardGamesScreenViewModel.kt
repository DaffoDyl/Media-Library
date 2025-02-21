package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.models.BoardGame
import com.daffodyl.medialibrary.repositories.BoardGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BoardGamesScreenViewModel(
    private val boardGamesRepository: BoardGamesRepository
): ViewModel() {
    private val _boardGames = MutableStateFlow(emptyList<BoardGame>())
    val boardGames: StateFlow<List<BoardGame>> = _boardGames

    init {
        viewModelScope.launch(Dispatchers.Default) {
            boardGamesRepository.boardGames.collect {
                _boardGames.value = it
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                BoardGamesScreenViewModel(
                    application.boardGamesRepository,
                )
            }
        }
    }
}