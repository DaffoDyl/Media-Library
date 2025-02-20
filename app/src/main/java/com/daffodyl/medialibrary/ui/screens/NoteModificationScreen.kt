package com.daffodyl.medialibrary.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.daffodyl.medialibrary.MediaLibraryApplication
import com.daffodyl.medialibrary.viewmodels.NoteModificationScreenViewModel

@Composable
fun NoteModificationScreen(
    id: Int?,
    goBack: () -> Unit,
    viewModel: NoteModificationScreenViewModel = viewModel(
        factory = NoteModificationScreenViewModel.Factory,
        extras = MutableCreationExtras().apply {
            this[NoteModificationScreenViewModel.NOTE_ID_KEY] = id
            this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
        }
    )
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.End
    ){
        val title by viewModel.title.collectAsState()
        val content by viewModel.content.collectAsState()
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { viewModel.setTitle(it) },
            label = { Text("Title") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth().weight(1f),
            value = content,
            onValueChange = { viewModel.setContent(it) },
            label = { Text("Content") },
        )
        Button(onClick = {
            viewModel.saveNote()
            goBack()
        }) {
            Text("Save")
        }
    }
}