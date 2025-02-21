package com.daffodyl.medialibrary

import android.app.Application
import com.daffodyl.medialibrary.repositories.BoardGamesRepository
import com.daffodyl.medialibrary.repositories.BooksRepository
import com.daffodyl.medialibrary.repositories.MoviesRepository
import com.daffodyl.medialibrary.repositories.VideoGamesRepository

class MediaLibraryApplication : Application() {
    val boardGamesRepository = BoardGamesRepository
    val booksRepository = BooksRepository
    val moviesRepository = MoviesRepository
    val videoGamesRepository = VideoGamesRepository
}