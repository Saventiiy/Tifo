package com.github.tifo.data.util

fun String.splitFullName(): Pair<String, String> {
    val parts = this.split("/")
    return Pair(parts[0], parts[1])
}

fun makeFullName(owner: String, repo: String): String = "$owner/$repo"
