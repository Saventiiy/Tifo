package com.github.tifo.screens.detailed

import com.github.tifo.screens.detailed.model.DetailedData

internal class Detailed {

    sealed class ViewState {
        object Loading : ViewState()
        data class Display(val detailedData: DetailedData) : ViewState()
    }

    sealed class Event {
        data class OnSearchClicked(val search: String) : Event()
    }

    sealed class Effect {
        data class Error(val error: String) : Effect()
    }
}