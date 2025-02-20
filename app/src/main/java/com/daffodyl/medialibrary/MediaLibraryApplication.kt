package com.daffodyl.medialibrary

import android.app.Application
import com.daffodyl.medialibrary.repositories.NotePagesRepository

class MediaLibraryApplication : Application() {
    val notePagesRepository = NotePagesRepository
}