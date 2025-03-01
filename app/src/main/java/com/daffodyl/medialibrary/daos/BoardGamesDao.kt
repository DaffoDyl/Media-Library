package com.daffodyl.medialibrary.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.daffodyl.medialibrary.models.BoardGame

@Dao
abstract class BoardGamesDao {
    @Query("SELECT * FROM BoardGame")
    abstract suspend fun getAllBoardGames(): List<BoardGame>

    @Insert
    abstract suspend fun insertBoardGame(boardGame: BoardGame)

    @Update
    abstract suspend fun updateBoardGame(boardGame: BoardGame)
}