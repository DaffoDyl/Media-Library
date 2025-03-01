package com.daffodyl.medialibrary.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.daffodyl.medialibrary.ui.components.LabeledMediaValue
import com.daffodyl.medialibrary.viewmodels.VideoGameScreenViewModel

@Composable
fun VideoGameScreen(
    id: Long,
    goBack: () -> Unit,
    goToCreateVideoGame: (id: Long) -> Unit,
    viewModel: VideoGameScreenViewModel = viewModel(
        factory = VideoGameScreenViewModel.Factory,
        extras = MutableCreationExtras().apply {
            this[VideoGameScreenViewModel.VIDEO_GAME_ID_KEY] = id
            this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
        }
    )
) {
    Column {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(0.9f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ){
            val videoGame by viewModel.videoGame.collectAsState()
            Text(
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                text = "${videoGame?.title}",
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            LabeledMediaValue("${videoGame?.developer}", "Developer")
            LabeledMediaValue("${videoGame?.genre}", "Genre")
            LabeledMediaValue("${videoGame?.rating}", "Rating")
            LabeledMediaValue("${videoGame?.platform}", "Platform")
            LabeledMediaValue("${videoGame?.notes}", "Notes")

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
        ) {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    goBack()
                    viewModel.deleteVideoGame()
                },
            ) {
                Text("Delete")
            }
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {goToCreateVideoGame(id)}
            ) {
                Text("Edit")
            }
        }
    }

}