package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieScreenViewModel(
    private val movieId: Long?,
    private val moviesRepository: MoviesRepository
): ViewModel() {
    private val _title = MutableStateFlow("")
    private val _format = MutableStateFlow("")
    private val _rating = MutableStateFlow("")
    private val _runtime = MutableStateFlow(0L)
    private val _genre = MutableStateFlow("")
    private val _notes = MutableStateFlow("")

    val title: StateFlow<String> = _title
    val format: StateFlow<String> = _format
    val rating: StateFlow<String> = _rating
    val runtime: StateFlow<Long> = _runtime
    val genre: StateFlow<String> = _genre
    val notes: StateFlow<String> = _notes

    fun deleteMovie() {
        if (movieId != null) {
            viewModelScope.launch(Dispatchers.Default) {
                moviesRepository.deleteMovie(movieId)
            }
        }
    }

    init {
        if (movieId != null) {
            viewModelScope.launch(Dispatchers.Default) {
                moviesRepository.movies.collect { movies ->
                    val movie = movies.find { it.id == movieId }
                    if (movie != null) {
                        _title.value = movie.title
                        _format.value = movie.format
                        _rating.value = movie.rating
                        _runtime.value = movie.runtime
                        _genre.value = movie.genre
                        _notes.value = movie.notes
                    }
                }
            }
        }
    }

    companion object {
        var MOVIE_ID_KEY = object : CreationExtras.Key<Long?> {}

        val Factory = viewModelFactory {
            initializer {
                val movieId = this[MOVIE_ID_KEY]
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                MovieScreenViewModel(
                    movieId,
                    application.moviesRepository
                )
            }
        }
    }
}