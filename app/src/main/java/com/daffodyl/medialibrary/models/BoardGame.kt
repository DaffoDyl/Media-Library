package com.daffodyl.medialibrary.models

data class BoardGame(
    val id: Long,
    val title: String,
    val minPlayers: Long,
    val maxPlayers: Long,
    val genre: String,
    val notes: String
)
