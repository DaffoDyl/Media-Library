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
import com.daffodyl.medialibrary.viewmodels.BooksScreenViewModel

@Composable
fun BooksScreen(
    goToBook: (id: Long?) -> Unit,
    goToCreateBook: (id: Long?) -> Unit,
    viewModel: BooksScreenViewModel = viewModel(factory = BooksScreenViewModel.Factory)
) {
    val books by viewModel.books.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadBooks()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ){
        Text(
            fontSize = 24.sp,
            text="Books",
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
            items(books) {
                MediaBox(modifier, it.title) { goToBook(it.id) }
            }
        }

        Button(
            onClick = { goToCreateBook(null) },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
            ),
        ) {
            Text("+ Add New Book")
        }
    }
}