package com.daffodyl.medialibrary.models

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val format: String,
    val numPages: Int,
    val genre: String,
    val notes: String
)
