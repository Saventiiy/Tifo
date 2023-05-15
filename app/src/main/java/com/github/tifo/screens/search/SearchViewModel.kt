package com.github.tifo.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tifo.domain.usecase.GetObservableRepositoriesUseCase
import com.github.tifo.util.EventHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SearchViewModel @Inject constructor(
    private val getObservableRepositoriesUseCase: GetObservableRepositoriesUseCase
) : ViewModel(), EventHandler<Search.Event> {

    private val _viewState: MutableStateFlow<Search.ViewState> =
        MutableStateFlow(Search.ViewState.Display(emptyList()))
    val viewState: StateFlow<Search.ViewState> = _viewState

    private val _effect: MutableSharedFlow<Search.Effect> = MutableSharedFlow()
    val effect = _effect.asSharedFlow()

    private var job: Job? = null

    override fun obtainEvent(event: Search.Event) {
        when (event) {
            is Search.Event.OnSearchClicked -> {
                getSearchRepositories(event.search)
            }
        }
    }

    private fun getSearchRepositories(search: String) {
        job?.cancel()
        job = getObservableRepositoriesUseCase(search)
            .onStart { emitViewState(Search.ViewState.Loading) }
            .onEach { emitViewState(Search.ViewState.Display(it)) }
            .catch {
                emitViewState(Search.ViewState.Display(emptyList()))
                emitEffect(Search.Effect.Error(it.message.toString()))
            }
            .launchIn(viewModelScope)
    }

    private fun emitViewState(state: Search.ViewState) {
        _viewState.value = state
    }

    private fun emitEffect(effect: Search.Effect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
}