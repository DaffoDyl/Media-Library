package com.daffodyl.medialibrary.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daffodyl.medialibrary.ui.components.MediaBox

@Composable
fun HomeScreen(
    goToBoardGames: () -> Unit,
    goToBooks: () -> Unit,
    goToMovies: () -> Unit,
    goToVideoGames: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        val modifier = Modifier.weight(1f).aspectRatio(1f)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MediaBox(modifier, "Books", goToBooks)
            MediaBox(modifier, "Movies", goToMovies)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MediaBox(modifier, "Board Games", goToBoardGames)
            MediaBox(modifier, "Video Games", goToVideoGames)
        }
    }
}