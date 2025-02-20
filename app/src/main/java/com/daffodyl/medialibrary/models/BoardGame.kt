package com.daffodyl.medialibrary.models

data class BoardGame(
    val id: Int,
    val title: String,
    val minPlayers: Int,
    val maxPlayers: Int,
    val genre: String,
    val notes: String
)
