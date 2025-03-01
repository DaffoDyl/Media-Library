package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.repositories.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookScreenViewModel(
    private val bookId: Long?,
    private val booksRepository: BooksRepository
): ViewModel() {
    private val _title = MutableStateFlow("")
    private val _author = MutableStateFlow("")
    private val _format = MutableStateFlow("")
    private val _numPages = MutableStateFlow(0L)
    private val _genre = MutableStateFlow("")
    private val _notes = MutableStateFlow("")

    val title: StateFlow<String> = _title
    val author: StateFlow<String> = _author
    val format: StateFlow<String> = _format
    val numPages: StateFlow<Long> = _numPages
    val genre: StateFlow<String> = _genre
    val notes: StateFlow<String> = _notes

    fun deleteBook() {
        if (bookId != null) {
            viewModelScope.launch(Dispatchers.Default) {
                booksRepository.deleteBook(bookId)
            }
        }
    }

    init {
        if (bookId != null) {
            viewModelScope.launch(Dispatchers.Default) {
                booksRepository.books.collect { books ->
                    val book = books.find { it.id == bookId }
                    if (book != null) {
                        _title.value = book.title
                        _author.value = book.author
                        _format.value = book.format
                        _numPages.value = book.numPages
                        _genre.value = book.genre
                        _notes.value = book.notes
                    }
                }
            }
        }
    }

    companion object {
        var BOARD_GAME_ID_KEY = object : CreationExtras.Key<Long?> {}

        val Factory = viewModelFactory {
            initializer {
                val bookId = this[BOARD_GAME_ID_KEY]
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                BookScreenViewModel(
                    bookId,
                    application.booksRepository
                )
            }
        }
    }
}