package com.daffodyl.medialibrary.repositories

import com.daffodyl.medialibrary.models.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object BooksRepository {
    private var idCounter = 0
    private val _books = MutableStateFlow(emptyList<Book>())
    val books: StateFlow<List<Book>> = _books

    fun addBooks(
        title: String,
        author: String,
        format: String,
        numPages: Int,
        genre: String,
        notes: String
    ) {
        val newBook = Book(
            id = ++idCounter,
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
        id: Int,
        title: String,
        author: String,
        format: String,
        numPages: Int,
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
        id: Int
    ) {
        _books.value = _books.value.filter { it.id != id }
    }
}