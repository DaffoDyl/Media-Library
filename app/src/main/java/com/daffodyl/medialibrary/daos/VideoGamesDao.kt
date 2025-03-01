package com.daffodyl.medialibrary.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.daffodyl.medialibrary.models.VideoGame

@Dao
abstract class VideoGamesDao {
    @Query("SELECT * FROM VideoGame")
    abstract suspend fun getAllVideoGames(): List<VideoGame>

    @Insert
    abstract suspend fun insertVideoGame(videoGame: VideoGame)

    @Update
    abstract suspend fun updateVideoGame(videoGame: VideoGame)

    @Delete
    abstract suspend fun deleteVideoGame(videoGame: VideoGame)
}