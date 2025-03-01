package com.daffodyl.medialibrary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.models.Book
import com.daffodyl.medialibrary.repositories.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BooksScreenViewModel(
    private val booksRepository: BooksRepository
): ViewModel() {
    private val _books = MutableStateFlow(emptyList<Book>())
    val books: StateFlow<List<Book>> = _books

    fun loadBooks() {
        viewModelScope.launch {
            booksRepository.loadBooks()
        }
    }

    init {
        viewModelScope.launch {
            booksRepository.books.collect {
                _books.value = it
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                BooksScreenViewModel(
                    application.booksRepository,
                )
            }
        }
    }
}