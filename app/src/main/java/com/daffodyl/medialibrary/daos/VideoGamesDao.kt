package com.daffodyl.medialibrary.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.daffodyl.medialibrary.models.VideoGame

@Dao
abstract class VideoGamesDao {
    @Query("SELECT * FROM VideoGame")
    abstract suspend fun getAllVideoGames(): List<VideoGame>

    @Insert
    abstract suspend fun insertVideoGame(boardGame: VideoGame)

    @Update
    abstract suspend fun updateVideoGame(boardGame: VideoGame)
}