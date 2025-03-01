package com.daffodyl.medialibrary.repositories

import com.daffodyl.medialibrary.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object MoviesRepository {
    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies: StateFlow<List<Movie>> = _movies

    fun addMovies(
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
        _movies.value += newMovie
    }

    fun updateMovie(
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
        _movies.value = _movies.value.map { movie ->
            if (movie.id == id) {
                updatedMovie
            } else {
                movie
            }
        }
    }

    fun deleteMovie(
        id: Long
    ) {
        _movies.value = _movies.value.filter { it.id != id }
    }
}