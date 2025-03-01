package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.models.BoardGame
import com.daffodyl.medialibrary.models.Book
import com.daffodyl.medialibrary.models.Movie
import com.daffodyl.medialibrary.models.VideoGame
import com.daffodyl.medialibrary.repositories.BoardGamesRepository
import com.daffodyl.medialibrary.repositories.BooksRepository
import com.daffodyl.medialibrary.repositories.MoviesRepository
import com.daffodyl.medialibrary.repositories.VideoGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val boardGamesRepository: BoardGamesRepository,
    private val booksRepository: BooksRepository,
    private val moviesRepository: MoviesRepository,
    private val videoGamesRepository: VideoGamesRepository
): ViewModel() {
    private val _boardGames = MutableStateFlow(emptyList<BoardGame>())
    private val _books = MutableStateFlow(emptyList<Book>())
    private val _movies = MutableStateFlow(emptyList<Movie>())
    private val _videoGames = MutableStateFlow(emptyList<VideoGame>())

    val boardGames: StateFlow<List<BoardGame>> = _boardGames
    val books: StateFlow<List<Book>> = _books
    val movies: StateFlow<List<Movie>> = _movies
    val videoGames: StateFlow<List<VideoGame>> = _videoGames

    fun loadMedias() {
        viewModelScope.launch {
            boardGamesRepository.loadBoardGames()
            booksRepository.loadBooks()
            moviesRepository.loadMovies()
            videoGamesRepository.loadVideoGames()
        }
    }

    init {
        viewModelScope.launch {
            boardGamesRepository.boardGames.collect {
                _boardGames.value = it
            }
        }
        viewModelScope.launch {
            booksRepository.books.collect {
                _books.value = it
            }
        }
        viewModelScope.launch {
            moviesRepository.movies.collect {
                _movies.value = it
            }
        }
        viewModelScope.launch {
            videoGamesRepository.videoGames.collect {
                _videoGames.value = it
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                HomeScreenViewModel(
                    application.boardGamesRepository,
                    application.booksRepository,
                    application.moviesRepository,
                    application.videoGamesRepository,
                )
            }
        }
    }
}