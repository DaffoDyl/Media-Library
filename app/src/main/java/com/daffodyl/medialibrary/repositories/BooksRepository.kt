package com.daffodyl.medialibrary.repositories

import com.daffodyl.medialibrary.models.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object BooksRepository {
    private val _books = MutableStateFlow(emptyList<Book>())
    val books: StateFlow<List<Book>> = _books

    fun addBooks(
        title: String,
        author: String,
        format: String,
        numPages: Long,
        genre: String,
        notes: String
    ) {
        val newBook = Book(
            title = title,
            author = author,
            format = format,
            numPages = numPages,
            genre = genre,
            notes = notes
        )
        _books.value += newBook
    }

    fun updateBook(
        id: Long,
        title: String,
        author: String,
        format: String,
        numPages: Long,
        genre: String,
        notes: String
    ) {
        val updatedBook = Book(
            id = id,
            title = title,
            author = author,
            format = format,
            numPages = numPages,
            genre = genre,
            notes = notes

        )
        _books.value = _books.value.map { book ->
            if (book.id == id) {
                updatedBook
            } else {
                book
            }
        }
    }

    fun deleteBook(
        id: Long
    ) {
        _books.value = _books.value.filter { it.id != id }
    }
}