package com.daffodyl.medialibrary

import kotlinx.serialization.Serializable

class Destinations {
    @Serializable object Home
    @Serializable object BoardGames
    @Serializable object Books
    @Serializable object Movies
    @Serializable object VideoGames
    @Serializable class BoardGame(val boardGameId: Long? = null)
    @Serializable class Book(val bookId: Long? = null)
    @Serializable class Movie(val movieId: Long? = null)
    @Serializable class VideoGame(val videoGameId: Long? = null)
    @Serializable class CreateBoardGame(val boardGameId: Long? = null)
    @Serializable class CreateBook(val bookId: Long? = null)
    @Serializable class CreateMovie(val movieId: Long? = null)
    @Serializable class CreateVideoGame(val videoGameId: Long? = null)
}