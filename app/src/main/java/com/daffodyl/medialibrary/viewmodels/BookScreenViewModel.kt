package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.models.Book
import com.daffodyl.medialibrary.repositories.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookScreenViewModel(
    private val booksRepository: BooksRepository,
    private val bookId: Long
): ViewModel() {
    private val _book = MutableStateFlow<Book?>(null)
    val book: StateFlow<Book?> = _book

    fun deleteBook() {
        viewModelScope.launch(Dispatchers.Default) {
            booksRepository.deleteBook(bookId)
        }
    }

    init {
        viewModelScope.launch {
            booksRepository.books.collect { books ->
                _book.value = books.find { it.id == bookId }
            }
        }
    }

    companion object {
        val BOOK_ID_KEY = object: CreationExtras.Key<Long> {}
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                val bookId = this[BOOK_ID_KEY] as Long
                BookScreenViewModel(
                    application.booksRepository,
                    bookId
                )
            }
        }
    }
}