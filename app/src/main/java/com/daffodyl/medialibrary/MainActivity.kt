package com.daffodyl.medialibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.daffodyl.medialibrary.ui.screens.NoteModificationScreen
import com.daffodyl.medialibrary.ui.screens.NotesScreen
import com.daffodyl.medialibrary.ui.theme.MediaLibraryTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MediaLibraryTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("Note Pages") },
                        )
                    },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Destinations.Notes,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Destinations.Notes> {
                            NotesScreen(
                                goToNoteModification = { id -> navController.navigate(Destinations.NoteModification(id)) },
                            )
                        }
                        composable<Destinations.NoteModification> {
                            NoteModificationScreen(
                                id = it.toRoute<Destinations.NoteModification>().noteId,
                                goBack = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }
}
