package com.daffodyl.medialibrary.repositories

import com.daffodyl.medialibrary.models.BoardGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object BoardGamesRepository {
    private var idCounter = 0L
    private val _boardGames = MutableStateFlow(emptyList<BoardGame>())
    val boardGames: StateFlow<List<BoardGame>> = _boardGames

    fun addBoardGames(
        title: String,
        minPlayers: Long,
        maxPlayers: Long,
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
        id: Long,
        title: String,
        minPlayers: Long,
        maxPlayers: Long,
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
        id: Long
    ) {
        _boardGames.value = _boardGames.value.filter { it.id != id }
    }
}