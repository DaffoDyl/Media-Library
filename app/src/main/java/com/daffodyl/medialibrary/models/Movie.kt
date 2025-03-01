package com.daffodyl.medialibrary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo val title: String,
    @ColumnInfo val format: String,
    @ColumnInfo val rating: String,
    @ColumnInfo val runtime: Long,
    @ColumnInfo val genre: String,
    @ColumnInfo val notes: String
)
