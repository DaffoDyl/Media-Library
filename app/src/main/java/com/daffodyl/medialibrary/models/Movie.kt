package com.daffodyl.medialibrary.models

data class Movie(
    val id: Int,
    val title: String,
    val format: String,
    val rating: String,
    val runtime: Int,
    val genre: String,
    val notes: String
)
