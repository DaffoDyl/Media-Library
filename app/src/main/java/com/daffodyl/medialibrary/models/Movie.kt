package com.daffodyl.medialibrary.models

data class Movie(
    val id: Int,
    val title: String,
    val genre: String,
    val rating: String,
    val runtime: Int,
    val format: String,
    val notes: String
)
