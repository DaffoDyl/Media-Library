package com.daffodyl.medialibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.daffodyl.medialibrary.ui.screens.BoardGameScreen
import com.daffodyl.medialibrary.ui.screens.BoardGamesScreen
import com.daffodyl.medialibrary.ui.screens.CreateBoardGameScreen
import com.daffodyl.medialibrary.ui.screens.HomeScreen
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
                            title = { Text("Media Library") },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            )
                        )
                    },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Destinations.Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Destinations.Home> {
                            HomeScreen(
                                goToBoardGames = { navController.navigate(Destinations.BoardGames) },
                            )
                        }
                        composable<Destinations.BoardGames> {
                            BoardGamesScreen(
                                goToBoardGame = { id -> navController.navigate(Destinations.BoardGame(id)) },
                                goToCreateBoardGame = { id -> navController.navigate(Destinations.CreateBoardGame(id)) },
                            )
                        }
                        composable<Destinations.BoardGame> {
                            BoardGameScreen(
                                id = it.toRoute<Destinations.BoardGame>().boardGameId,
                                goBack = { navController.popBackStack() },
                                goToCreateBoardGame = { id -> navController.navigate(Destinations.CreateBoardGame(id)) },
                            )
                        }
                        composable<Destinations.CreateBoardGame> {
                            CreateBoardGameScreen(
                                id = it.toRoute<Destinations.CreateBoardGame>().boardGameId,
                                goBack = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }
}
