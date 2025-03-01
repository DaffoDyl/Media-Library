package com.daffodyl.medialibrary.models

data class Book(
    val id: Long,
    val title: String,
    val author: String,
    val format: String,
    val numPages: Long,
    val genre: String,
    val notes: String
)
