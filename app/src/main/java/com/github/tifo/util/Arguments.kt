package com.github.tifo.util

import androidx.navigation.NavBackStackEntry

fun NavBackStackEntry.getArgument(key: String): String {
    return requireNotNull(this.arguments?.getString(key)).removeSurrounding("{", "}")
}