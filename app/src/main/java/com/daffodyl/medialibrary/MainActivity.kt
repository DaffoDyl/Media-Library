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
import com.daffodyl.medialibrary.ui.screens.BookScreen
import com.daffodyl.medialibrary.ui.screens.BooksScreen
import com.daffodyl.medialibrary.ui.screens.CreateBookScreen
import com.daffodyl.medialibrary.ui.screens.CreateBoardGameScreen
import com.daffodyl.medialibrary.ui.screens.CreateMovieScreen
import com.daffodyl.medialibrary.ui.screens.CreateVideoGameScreen
import com.daffodyl.medialibrary.ui.screens.HomeScreen
import com.daffodyl.medialibrary.ui.screens.MovieScreen
import com.daffodyl.medialibrary.ui.screens.MoviesScreen
import com.daffodyl.medialibrary.ui.screens.VideoGameScreen
import com.daffodyl.medialibrary.ui.screens.VideoGamesScreen
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
                                goToBooks = { navController.navigate(Destinations.Books) },
                                goToMovies = { navController.navigate(Destinations.Movies) },
                                goToVideoGames = { navController.navigate(Destinations.VideoGames) }
                            )
                        }
                        // Board Games
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
                        // Books
                        composable<Destinations.Books> {
                            BooksScreen(
                                goToBook = { id -> navController.navigate(Destinations.Book(id)) },
                                goToCreateBook = { id -> navController.navigate(Destinations.CreateBook(id)) },
                            )
                        }
                        composable<Destinations.Book> {
                            BookScreen(
                                id = it.toRoute<Destinations.Book>().bookId,
                                goBack = { navController.popBackStack() },
                                goToCreateBook = { id -> navController.navigate(Destinations.CreateBook(id)) },
                            )
                        }
                        composable<Destinations.CreateBook> {
                            CreateBookScreen(
                                id = it.toRoute<Destinations.CreateBook>().bookId,
                                goBack = { navController.popBackStack() },
                            )
                        }
                        // Movies
                        composable<Destinations.Movies> {
                            MoviesScreen(
                                goToMovie = { id -> navController.navigate(Destinations.Movie(id)) },
                                goToCreateMovie = { id -> navController.navigate(Destinations.CreateMovie(id)) },
                            )
                        }
                        composable<Destinations.Movie> {
                            MovieScreen(
                                id = it.toRoute<Destinations.Movie>().movieId,
                                goBack = { navController.popBackStack() },
                                goToCreateMovie = { id -> navController.navigate(Destinations.CreateMovie(id)) },
                            )
                        }
                        composable<Destinations.CreateMovie> {
                            CreateMovieScreen(
                                id = it.toRoute<Destinations.CreateMovie>().movieId,
                                goBack = { navController.popBackStack() },
                            )
                        }
                        // Video Games
                        composable<Destinations.VideoGames> {
                            VideoGamesScreen(
                                goToVideoGame = { id -> navController.navigate(Destinations.VideoGame(id)) },
                                goToCreateVideoGame = { id -> navController.navigate(Destinations.CreateVideoGame(id)) },
                            )
                        }
                        composable<Destinations.VideoGame> {
                            VideoGameScreen(
                                id = it.toRoute<Destinations.VideoGame>().videoGameId,
                                goBack = { navController.popBackStack() },
                                goToCreateVideoGame = { id -> navController.navigate(Destinations.CreateVideoGame(id)) },
                            )
                        }
                        composable<Destinations.CreateVideoGame> {
                            CreateVideoGameScreen(
                                id = it.toRoute<Destinations.CreateVideoGame>().videoGameId,
                                goBack = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }
}
