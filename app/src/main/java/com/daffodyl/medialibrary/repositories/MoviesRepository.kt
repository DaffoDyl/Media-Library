package com.daffodyl.medialibrary.repositories

import com.daffodyl.medialibrary.daos.MoviesDao
import com.daffodyl.medialibrary.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MoviesRepository(
    private val moviesDao: MoviesDao
) {
    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies: StateFlow<List<Movie>> = _movies

    suspend fun loadMovies() {
        _movies.value = moviesDao.getAllMovies()
    }

    suspend fun addMovies(
        title: String,
        format: String,
        rating: String,
        runtime: Long,
        genre: String,
        notes: String
    ) {
        val newMovie = Movie(
            title = title,
            format = format,
            rating = rating,
            runtime = runtime,
            genre = genre,
            notes = notes
        )
        newMovie.id = moviesDao.insertMovie(newMovie)
        _movies.value += newMovie
    }

    suspend fun updateMovie(
        id: Long,
        title: String,
        format: String,
        rating: String,
        runtime: Long,
        genre: String,
        notes: String
    ) {
        val updatedMovie = Movie(
            id = id,
            title = title,
            format = format,
            rating = rating,
            runtime = runtime,
            genre = genre,
            notes = notes
        )
        moviesDao.updateMovie(updatedMovie)
        _movies.value = _movies.value.map { movie ->
            if (movie.id == id) {
                updatedMovie
            } else {
                movie
            }
        }
    }

    suspend fun deleteMovie(
        movie: Movie?
    ) {
        if (movie != null) {
            moviesDao.deleteMovie(movie)
            _movies.value = _movies.value.filter { it.id != movie.id }
        }
    }
}