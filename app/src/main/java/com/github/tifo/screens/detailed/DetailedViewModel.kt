package com.github.tifo.screens.detailed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tifo.data.model.dto.branches.Branches
import com.github.tifo.data.model.dto.request.Link
import com.github.tifo.data.util.makeFullName
import com.github.tifo.domain.usecase.BranchesUseCase
import com.github.tifo.domain.usecase.ContributorsUseCase
import com.github.tifo.domain.usecase.GetObservableBranchesUseCase
import com.github.tifo.domain.usecase.GetObservableContributorsUseCase
import com.github.tifo.screens.detailed.model.DetailedData
import com.github.tifo.util.EventHandler
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class DetailedViewModel @AssistedInject constructor(
    private val branchesUseCase: BranchesUseCase,
    private val contributorsUseCase: ContributorsUseCase,
    private val getObservableBranchesUseCase: GetObservableBranchesUseCase,
    private val getObservableContributorsUseCase: GetObservableContributorsUseCase,
    @Assisted("owner") private val owner: String,
    @Assisted("repo") private val repo: String
) : ViewModel(), EventHandler<Detailed.Event> {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("owner") owner: String,
            @Assisted("repo") repo: String
        ): DetailedViewModel
    }

    private val _viewState = MutableStateFlow<Detailed.ViewState>(Detailed.ViewState.Loading)
    val viewState: StateFlow<Detailed.ViewState> = _viewState

    private val _effect: MutableSharedFlow<Detailed.Effect> = MutableSharedFlow()
    val effect = _effect.asSharedFlow()

    init {
        getBranches()
        getContributors()
        getObservableBranches()
    }

    override fun obtainEvent(event: Detailed.Event) {
        when (event) {
            is Detailed.Event.OnSearchClicked -> {
                getBranches()
            }
        }
    }

    private fun getObservableBranches() {
        getObservableBranchesUseCase(makeFullName(owner, repo))
            .onStart { emitViewState(Detailed.ViewState.Loading) }
            .onEach { getObservableContributors(it) }
            .catch {
                emitViewState(
                    Detailed.ViewState.Display(DetailedData(emptyList(), emptyList(), ""))
                )
                emitEffect(Detailed.Effect.Error(it.message.toString()))
            }
            .launchIn(viewModelScope)
    }

    private fun getObservableContributors(branches: List<Branches>) {
        getObservableContributorsUseCase(makeFullName(owner, repo))
            .onStart { emitViewState(Detailed.ViewState.Loading) }
            .onEach { emitViewState(Detailed.ViewState.Display(DetailedData(branches, it, repo))) }
            .catch {
                emitViewState(
                    Detailed.ViewState.Display(DetailedData(emptyList(), emptyList(), ""))
                )
                emitEffect(Detailed.Effect.Error(it.message.toString()))
            }
            .launchIn(viewModelScope)
    }


    private fun getBranches() {
        viewModelScope.launch {
            try {
                branchesUseCase(Link(owner, repo))
            } catch (exception: Exception) {
                emitEffect(Detailed.Effect.Error(exception.message.toString()))
            }
        }
    }

    private fun getContributors() {
        viewModelScope.launch {
            try {
                contributorsUseCase(Link(owner, repo))
            } catch (exception: Exception) {
                emitEffect(Detailed.Effect.Error(exception.message.toString()))
            }
        }
    }

    private fun emitViewState(state: Detailed.ViewState) {
        _viewState.value = state
    }

    private fun emitEffect(effect: Detailed.Effect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
}