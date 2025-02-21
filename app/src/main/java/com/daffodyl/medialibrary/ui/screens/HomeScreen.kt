package com.daffodyl.medialibrary.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.ui.components.MediaBox
import com.daffodyl.medialibrary.viewmodels.BooksScreenViewModel
import com.daffodyl.medialibrary.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(
    goToBoardGames: () -> Unit,
    goToBooks: () -> Unit,
    goToMovies: () -> Unit,
    goToVideoGames: () -> Unit,
    viewModel: HomeScreenViewModel = viewModel(
        factory = HomeScreenViewModel.Factory,
        extras = MutableCreationExtras().apply {
            this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
        }
    )
) {
    val books by viewModel.books.collectAsState()
    val movies by viewModel.movies.collectAsState()
    val boardGames by viewModel.boardGames.collectAsState()
    val videoGames by viewModel.videoGames.collectAsState()
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
            MediaBox(modifier, "Books (" + books.size + ")", goToBooks)
            MediaBox(modifier, "Movies(" + movies.size + ")", goToMovies)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MediaBox(modifier, "Board Games (" + boardGames.size + ")", goToBoardGames)
            MediaBox(modifier, "Video Games (" + videoGames.size + ")", goToVideoGames)
        }
    }
}