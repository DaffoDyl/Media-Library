package com.daffodyl.medialibrary.repositories

import com.daffodyl.medialibrary.daos.BoardGamesDao
import com.daffodyl.medialibrary.models.BoardGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BoardGamesRepository(
    private val boardGamesDao: BoardGamesDao
) {
    private val _boardGames = MutableStateFlow(emptyList<BoardGame>())
    val boardGames: StateFlow<List<BoardGame>> = _boardGames

    suspend fun loadBoardGames() {
        _boardGames.value = boardGamesDao.getAllBoardGames()
    }

    suspend fun addBoardGames(
        title: String,
        minPlayers: Long,
        maxPlayers: Long,
        genre: String,
        notes: String
    ) {
        val newBoardGame = BoardGame(
            title = title,
            minPlayers = minPlayers,
            maxPlayers = maxPlayers,
            genre = genre,
            notes = notes
        )
        newBoardGame.id = boardGamesDao.insertBoardGame(newBoardGame)
        _boardGames.value += newBoardGame
    }

    suspend fun updateBoardGame(
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
        boardGamesDao.updateBoardGame(updatedBoardGame)
        _boardGames.value = _boardGames.value.map { boardGame ->
            if (boardGame.id == id) {
                updatedBoardGame
            } else {
                boardGame
            }
        }
    }

    suspend fun deleteBoardGame(
        boardGame: BoardGame?
    ) {
        if (boardGame != null) {
            boardGamesDao.deleteBoardGame(boardGame)
            _boardGames.value = _boardGames.value.filter { it.id != boardGame.id }
        }
    }
}