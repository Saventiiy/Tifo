package com.github.tifo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.tifo.screens.detailed.DetailedScreen
import com.github.tifo.screens.search.SearchScreen
import com.github.tifo.theme.TifoTheme
import com.github.tifo.util.getArgument
import com.github.tifo.util.injectedViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(this.window, true)
        setContent {
            TifoTheme {
                Box(modifier = Modifier.statusBarsPadding()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.SearchScreen.route
                    ) {
                        composable(
                            route = Routes.SearchScreen.route
                        ) {
                            val viewModel = injectedViewModel {
                                component.searchViewModel
                            }
                            SearchScreen(viewModel, navController)
                        }
                        composable(
                            route = Routes.DetailedScreen.route,
                            arguments = listOf(
                                navArgument(Routes.ARG_OWNER) { type = NavType.StringType },
                                navArgument(Routes.ARG_REPO) { type = NavType.StringType }
                            )
                        ) {
                            val owner = it.getArgument(Routes.ARG_OWNER)
                            val repo = it.getArgument(Routes.ARG_REPO)

                            val viewModel = injectedViewModel {
                                component.detailedViewModel.create(owner, repo)
                            }
                            DetailedScreen(viewModel, navController)
                        }
                    }
                }
            }
        }
    }
}