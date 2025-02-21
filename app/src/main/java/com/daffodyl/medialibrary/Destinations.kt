package com.daffodyl.medialibrary

import kotlinx.serialization.Serializable

class Destinations {
    @Serializable object Home
    @Serializable object BoardGames
    @Serializable object Books
    @Serializable object Movies
    @Serializable object VideoGames
    @Serializable class BoardGame(val boardGameId: Int)
    @Serializable class Book(val bookId: Int)
    @Serializable class Movie(val movieId: Int)
    @Serializable class VideoGame(val videoGameId: Int)
    @Serializable class CreateBoardGame(val boardGameId: Int? = null)
    @Serializable class CreateBook(val bookId: Int? = null)
    @Serializable class CreateMovie(val movieId: Int? = null)
    @Serializable class CreateVideoGame(val videoGameId: Int? = null)
}