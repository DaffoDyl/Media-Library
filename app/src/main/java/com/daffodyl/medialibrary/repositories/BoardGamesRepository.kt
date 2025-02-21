package com.daffodyl.medialibrary.repositories

import com.daffodyl.medialibrary.models.BoardGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object BoardGamesRepository {
    private var idCounter = 0
    private val _boardGames = MutableStateFlow(emptyList<BoardGame>())
    val boardGames: StateFlow<List<BoardGame>> = _boardGames

    fun addBoardGames(
        title: String,
        minPlayers: Int,
        maxPlayers: Int,
        genre: String,
        notes: String
    ) {
        val newBoardGame = BoardGame(
            id = ++idCounter,
            title = title,
            minPlayers = minPlayers,
            maxPlayers = maxPlayers,
            genre = genre,
            notes = notes
        )
        _boardGames.value += newBoardGame
    }

    fun updateBoardGame(
        id: Int,
        title: String,
        minPlayers: Int,
        maxPlayers: Int,
        genre: String,
        notes: String
    ) {
        val updatedBoardGame = BoardGame(
            id = id,
            title = title,
            minPlayers = minPlayers,
            maxPlayers = maxPlayers,
            genre = genre,
            notes = notes

        )
        _boardGames.value = _boardGames.value.map { boardGame ->
            if (boardGame.id == id) {
                updatedBoardGame
            } else {
                boardGame
            }
        }
    }

    fun deleteBoardGame(
        id: Int
    ) {
        _boardGames.value = _boardGames.value.filter { it.id != id }
    }
}