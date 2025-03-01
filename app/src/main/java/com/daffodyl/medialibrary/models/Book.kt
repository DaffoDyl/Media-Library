package com.daffodyl.medialibrary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo val title: String,
    @ColumnInfo val author: String,
    @ColumnInfo val format: String,
    @ColumnInfo val numPages: Long,
    @ColumnInfo val genre: String,
    @ColumnInfo val notes: String
)
