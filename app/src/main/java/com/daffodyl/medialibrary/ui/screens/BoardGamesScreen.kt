package com.daffodyl.medialibrary.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.daffodyl.medialibrary.ui.components.MediaBox
import com.daffodyl.medialibrary.viewmodels.BoardGamesScreenViewModel

@Composable
fun BoardGamesScreen(
    goToBoardGame: (id: Long?) -> Unit,
    goToCreateBoardGame: (id: Long?) -> Unit,
    viewModel: BoardGamesScreenViewModel = viewModel(factory = BoardGamesScreenViewModel.Factory)
) {
    val boardGames by viewModel.boardGames.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadBoardGames()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ){
        Text(
            fontSize = 24.sp,
            text="Board Games",
        )

        LazyVerticalGrid (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(.95f),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),

            ) {
            val modifier = Modifier.aspectRatio(1f)
            items(boardGames) {
                MediaBox(modifier, it.title) { goToBoardGame(it.id) }
            }
        }

        Button(
            onClick = { goToCreateBoardGame(null) },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
            ),
        ) {
            Text("+ Add New Board Game")
        }
    }
}