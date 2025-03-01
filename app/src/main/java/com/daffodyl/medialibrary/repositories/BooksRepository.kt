package com.daffodyl.medialibrary.repositories

import com.daffodyl.medialibrary.daos.BooksDao
import com.daffodyl.medialibrary.models.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BooksRepository(
    private val booksDao: BooksDao
) {
    private val _books = MutableStateFlow(emptyList<Book>())
    val books: StateFlow<List<Book>> = _books

    suspend fun loadBooks() {
        _books.value = booksDao.getAllBooks()
    }

    suspend fun addBooks(
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
        newBook.id = booksDao.insertBook(newBook)
        _books.value += newBook
    }

    suspend fun updateBook(
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
        booksDao.updateBook(updatedBook)
        _books.value = _books.value.map { book ->
            if (book.id == id) {
                updatedBook
            } else {
                book
            }
        }
    }

    suspend fun deleteBook(
        book: Book?
    ) {
        if (book != null) {
            booksDao.deleteBook(book)
            _books.value = _books.value.filter { it.id != book.id }
        }
    }
}