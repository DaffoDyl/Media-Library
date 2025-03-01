package com.daffodyl.medialibrary.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.viewmodels.CreateMovieScreenViewModel
import com.daffodyl.medialibrary.ui.components.DialogButtons

@Composable
fun CreateMovieScreen(
    id: Int?,
    goBack: () -> Unit,
    viewModel: CreateMovieScreenViewModel = viewModel(
        factory = CreateMovieScreenViewModel.Factory,
        extras = MutableCreationExtras().apply {
            this[CreateMovieScreenViewModel.BOARD_GAME_ID_KEY] = id
            this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
        }
    )
) {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End
        ){
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Movie Details",
            )
            val title by viewModel.title.collectAsState()
            val format by viewModel.format.collectAsState()
            val rating by viewModel.rating.collectAsState()
            val runTime by viewModel.runTime.collectAsState()
            val genre by viewModel.genre.collectAsState()
            val notes by viewModel.notes.collectAsState()
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { viewModel.setTitle(it) },
                label = { Text("Title") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = format,
                onValueChange = { viewModel.setFormat(it) },
                label = { Text("Format") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = rating,
                onValueChange = { viewModel.setRating(it) },
                label = { Text("Rating") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = if(runTime != 0) runTime.toString() else "",
                onValueChange = {
                    try { viewModel.setRunTime(it.toInt()) }
                    catch (_: NumberFormatException) {}
                },
                label = { Text("Run Time") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = genre,
                onValueChange = { viewModel.setGenre(it) },
                label = { Text("Genre") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = notes,
                onValueChange = { viewModel.setNotes(it) },
                label = { Text("Notes") },
            )
            Spacer(modifier = Modifier.height(8.dp))
            DialogButtons(goBack) { viewModel.saveMovie() }
        }
    }
}
