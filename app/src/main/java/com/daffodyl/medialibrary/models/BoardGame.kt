package com.daffodyl.medialibrary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BoardGame(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo val title: String,
    @ColumnInfo val minPlayers: Long,
    @ColumnInfo val maxPlayers: Long,
    @ColumnInfo val genre: String,
    @ColumnInfo val notes: String
)
