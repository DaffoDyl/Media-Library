package com.daffodyl.medialibrary.repositories

import com.daffodyl.medialibrary.daos.VideoGamesDao
import com.daffodyl.medialibrary.models.VideoGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VideoGamesRepository(
    private val videoGamesDao: VideoGamesDao
) {
    private val _videoGames = MutableStateFlow(emptyList<VideoGame>())
    val videoGames: StateFlow<List<VideoGame>> = _videoGames

    suspend fun loadVideoGames() {
        _videoGames.value = videoGamesDao.getAllVideoGames()
    }

    suspend fun addVideoGames(
        title: String,
        developer: String,
        genre: String,
        rating: String,
        platform: String,
        notes: String
    ) {
        val newVideoGame = VideoGame(
            title = title,
            developer = developer,
            genre = genre,
            rating = rating,
            platform = platform,
            notes = notes
        )
        newVideoGame.id = videoGamesDao.insertVideoGame(newVideoGame)
        _videoGames.value += newVideoGame
    }

    suspend fun updateVideoGame(
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
        videoGamesDao.updateVideoGame(updatedVideoGame)
        _videoGames.value = _videoGames.value.map { videoGame ->
            if (videoGame.id == id) {
                updatedVideoGame
            } else {
                videoGame
            }
        }
    }

    suspend fun deleteVideoGame(
        videoGame: VideoGame?
    ) {
        if (videoGame != null) {
            videoGamesDao.deleteVideoGame(videoGame)
            _videoGames.value = _videoGames.value.filter { it.id != videoGame.id }
        }
    }
}