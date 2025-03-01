package com.daffodyl.medialibrary.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.daffodyl.medialibrary.models.Movie

@Dao
abstract class MoviesDao {
    @Query("SELECT * FROM Movie")
    abstract suspend fun getAllMovies(): List<Movie>

    @Insert
    abstract suspend fun insertMovie(boardGame: Movie)

    @Update
    abstract suspend fun updateMovie(boardGame: Movie)
}