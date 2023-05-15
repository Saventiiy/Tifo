package com.github.tifo.screens.search

import com.github.tifo.data.model.dto.repositories.RepositoriesItem

internal class Search {

    sealed class ViewState {
        object Loading : ViewState()
        data class Display(val repositories: List<RepositoriesItem>) : ViewState()
    }

    sealed class Event {
        data class OnSearchClicked(val search: String) : Event()
    }

    sealed class Effect {
        data class Error(val error: String) : Effect()
    }
}