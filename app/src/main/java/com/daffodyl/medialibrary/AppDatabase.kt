package com.daffodyl.medialibrary

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daffodyl.medialibrary.daos.BoardGamesDao
import com.daffodyl.medialibrary.daos.BooksDao
import com.daffodyl.medialibrary.daos.MoviesDao
import com.daffodyl.medialibrary.daos.VideoGamesDao
import com.daffodyl.medialibrary.models.BoardGame
import com.daffodyl.medialibrary.models.Book
import com.daffodyl.medialibrary.models.Movie
import com.daffodyl.medialibrary.models.VideoGame

@Database(
    entities = [BoardGame::class, Book::class, Movie::class, VideoGame::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val boardGamesDao: BoardGamesDao
    abstract val booksDao: BooksDao
    abstract val moviesDao: MoviesDao
    abstract val videoGamesDao: VideoGamesDao
}