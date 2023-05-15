package com.github.tifo

sealed class Routes(val route: String) {
    object SearchScreen : Routes("search_screen")
    object DetailedScreen : Routes("detailed_screen/{$ARG_OWNER}/{$ARG_REPO}")

    companion object {
        const val ARG_OWNER = "ARG_OWNER"
        const val ARG_REPO: String = "ARG_REPO"
    }
}
