package com.github.tifo.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.tifo.Routes
import com.github.tifo.data.util.splitFullName
import com.github.tifo.screens.search.view.SearchItem
import com.github.tifo.screens.search.view.SearchTopBar
import com.github.tifo.view.ErrorSnackbar
import com.github.tifo.view.LoadingView
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val viewState = viewModel.viewState.collectAsStateWithLifecycle()
    val searchState = remember { mutableStateOf("") }

    when (val state = viewState.value) {
        is Search.ViewState.Loading -> LoadingView()
        is Search.ViewState.Display -> {
            Scaffold(topBar = {
                SearchTopBar(searchState) {
                    viewModel.obtainEvent(
                        Search.Event.OnSearchClicked(searchState.value)
                    )
                }
            }) {
                Column(modifier = Modifier.padding(it)) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.repositories.size) { index ->
                            SearchItem(state.repositories[index]) { fullName ->
                                val link = fullName.splitFullName()
                                val route = Routes.DetailedScreen.route
                                    .replace(Routes.ARG_OWNER, link.first)
                                    .replace(Routes.ARG_REPO, link.second)
                                navController.navigate(route)
                            }
                            if (index != state.repositories.size) {
                                Divider(
                                    color = Color.LightGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp)
                                )
                            }
                        }
                    }
                }
            }
            ErrorSnackbar(hostState = snackbarHostState)
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is Search.Effect.Error -> scope.launch {
                    snackbarHostState.showSnackbar(effect.error)
                }
            }
        }
            .launchIn(this)
    }
}
