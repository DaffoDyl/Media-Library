package com.daffodyl.medialibrary.repositories

import com.daffodyl.medialibrary.models.VideoGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object VideoGamesRepository {
    private var idCounter = 0L
    private val _videoGames = MutableStateFlow(emptyList<VideoGame>())
    val videoGames: StateFlow<List<VideoGame>> = _videoGames

    fun addVideoGames(
        title: String,
        developer: String,
        genre: String,
        rating: String,
        platform: String,
        notes: String
    ) {
        val newVideoGame = VideoGame(
            id = ++idCounter,
            title = title,
            developer = developer,
            genre = genre,
            rating = rating,
            platform = platform,
            notes = notes
        )
        _videoGames.value += newVideoGame
    }

    fun updateVideoGame(
        id: Long,
        title: String,
        developer: String,
        genre: String,
        rating: String,
        platform: String,
        notes: String
    ) {
        val updatedVideoGame = VideoGame(
            id = id,
            title = title,
            developer = developer,
            rating = rating,
            platform = platform,
            genre = genre,
            notes = notes

        )
        _videoGames.value = _videoGames.value.map { videoGame ->
            if (videoGame.id == id) {
                updatedVideoGame
            } else {
                videoGame
            }
        }
    }

    fun deleteVideoGame(
        id: Long
    ) {
        _videoGames.value = _videoGames.value.filter { it.id != id }
    }
}