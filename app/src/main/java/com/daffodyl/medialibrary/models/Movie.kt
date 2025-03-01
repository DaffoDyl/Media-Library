package com.daffodyl.medialibrary.models

data class Movie(
    val id: Long,
    val title: String,
    val format: String,
    val rating: String,
    val runtime: Long,
    val genre: String,
    val notes: String
)
