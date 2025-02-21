package com.daffodyl.medialibrary

import android.app.Application
import com.daffodyl.medialibrary.repositories.BoardGamesRepository

class MediaLibraryApplication : Application() {
    val boardGamesRepository = BoardGamesRepository
}