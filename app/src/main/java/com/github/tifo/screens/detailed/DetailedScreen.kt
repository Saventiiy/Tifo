package com.github.tifo.screens.detailed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.tifo.R
import com.github.tifo.screens.detailed.view.BranchesItem
import com.github.tifo.screens.detailed.view.ContributorsItem
import com.github.tifo.screens.detailed.view.DetailedTopBar
import com.github.tifo.view.ErrorSnackbar
import com.github.tifo.view.LoadingView
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
internal fun DetailedScreen(
    viewModel: DetailedViewModel,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val viewState = viewModel.viewState.collectAsStateWithLifecycle()

    when (val state = viewState.value) {
        is Detailed.ViewState.Loading -> LoadingView()
        is Detailed.ViewState.Display -> {
            Scaffold(topBar = { DetailedTopBar(state.detailedData.name) }) {
                Column(modifier = Modifier.padding(it)) {
                    LazyColumn(modifier = Modifier.padding(12.dp)) {
                        item {
                            Text(
                                text = stringResource(id = R.string.branches)
                            )
                            if (state.detailedData.branches.isEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.there_is_no_branches),
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            } else {
                                state.detailedData.branches.forEachIndexed { index, branch ->
                                    BranchesItem(branch)
                                    if (index != state.detailedData.branches.lastIndex) {
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
                        item {
                            Text(
                                text = stringResource(id = R.string.contributors),
                                modifier = Modifier.padding(top = 24.dp)
                            )
                            if (state.detailedData.contributors.isEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.there_is_no_contributors),
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            } else {
                                state.detailedData.contributors.forEachIndexed { index, contributor ->
                                    ContributorsItem(contributor)
                                    if (index != state.detailedData.contributors.lastIndex) {
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
                }
            }
            ErrorSnackbar(hostState = snackbarHostState)
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is Detailed.Effect.Error -> scope.launch {
                    snackbarHostState.showSnackbar(effect.error)
                }
            }
        }
            .launchIn(this)
    }
}
