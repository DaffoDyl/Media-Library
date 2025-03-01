package com.daffodyl.medialibrary

import kotlinx.serialization.Serializable

class Destinations {
    @Serializable object Home
    @Serializable object BoardGames
    @Serializable object Books
    @Serializable object Movies
    @Serializable object VideoGames
    @Serializable class BoardGame(val boardGameId: Long)
    @Serializable class Book(val bookId: Long)
    @Serializable class Movie(val movieId: Long)
    @Serializable class VideoGame(val videoGameId: Long)
    @Serializable class CreateBoardGame(val boardGameId: Long? = null)
    @Serializable class CreateBook(val bookId: Long? = null)
    @Serializable class CreateMovie(val movieId: Long? = null)
    @Serializable class CreateVideoGame(val videoGameId: Long? = null)
}