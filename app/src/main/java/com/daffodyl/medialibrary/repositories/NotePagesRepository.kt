package com.daffodyl.medialibrary.repositories

import com.daffodyl.medialibrary.models.NotePage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object NotePagesRepository {
    private var idCounter = 0
    private val _notes = MutableStateFlow(emptyList<NotePage>())
    val notes: StateFlow<List<NotePage>> = _notes

    fun addNotePage(
        title: String,
        content: String
    ) {
        val newNotePage = NotePage(
            id = ++idCounter,
            title = title,
            content = content
        )
        _notes.value += newNotePage
    }

    fun updateNotePage(
        id: Int,
        title: String,
        content: String
    ) {
        val updatedNotePage = NotePage(
            id = id,
            title = title,
            content = content
        )
        _notes.value = _notes.value.map { notePage ->
            if (notePage.id == id) {
                updatedNotePage
            } else {
                notePage
            }
        }
    }

    fun deleteNotePage(
        id: Int
    ) {
        _notes.value = _notes.value.filter { it.id != id }
    }
}