package com.daffodyl.medialibrary.models

data class VideoGame(
    val id: Long,
    val title: String,
    val developer: String,
    val genre: String,
    val rating: String,
    val platform: String,
    val notes: String
)
