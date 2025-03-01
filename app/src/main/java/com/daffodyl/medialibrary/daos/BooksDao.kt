package com.daffodyl.medialibrary.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.daffodyl.medialibrary.models.Book

@Dao
abstract class BooksDao {
    @Query("SELECT * FROM Book")
    abstract suspend fun getAllBooks(): List<Book>

    @Insert
    abstract suspend fun insertBook(book: Book): Long

    @Update
    abstract suspend fun updateBook(book: Book)

    @Delete
    abstract suspend fun deleteBook(book: Book)
}