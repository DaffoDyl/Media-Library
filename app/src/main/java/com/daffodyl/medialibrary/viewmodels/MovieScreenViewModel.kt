package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.models.Movie
import com.daffodyl.medialibrary.repositories.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieScreenViewModel(
    private val moviesRepository: MoviesRepository,
    private val movieId: Long
): ViewModel() {
    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie

    fun deleteMovie() {
        viewModelScope.launch {
            moviesRepository.deleteMovie(_movie.value)
        }
    }

    init {
        viewModelScope.launch {
            moviesRepository.movies.collect { movies ->
                _movie.value = movies.find { it.id == movieId }
            }
        }
    }

    companion object {
        val MOVIE_ID_KEY = object: CreationExtras.Key<Long?> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val movieId = this[MOVIE_ID_KEY] as Long
                MovieScreenViewModel(
                    application.moviesRepository,
                    movieId
                )
            }
        }
    }
}