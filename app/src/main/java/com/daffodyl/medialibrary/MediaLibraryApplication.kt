package com.daffodyl.medialibrary

import android.app.Application
import androidx.room.Room
import com.daffodyl.medialibrary.repositories.BoardGamesRepository
import com.daffodyl.medialibrary.repositories.BooksRepository
import com.daffodyl.medialibrary.repositories.MoviesRepository
import com.daffodyl.medialibrary.repositories.VideoGamesRepository

class MediaLibraryApplication : Application() {
    private val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "media_library_database"
        ).build()
    }
    val boardGamesRepository by lazy { BoardGamesRepository(db.boardGamesDao) }
    val booksRepository by lazy { BooksRepository(db.booksDao) }
    val moviesRepository by lazy { MoviesRepository(db.moviesDao) }
    val videoGamesRepository by lazy { VideoGamesRepository(db.videoGamesDao) }
}