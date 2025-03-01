package com.daffodyl.medialibrary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoGame(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo val title: String,
    @ColumnInfo val developer: String,
    @ColumnInfo val genre: String,
    @ColumnInfo val rating: String,
    @ColumnInfo val platform: String,
    @ColumnInfo val notes: String
)
