package com.github.tifo.data.model.dto.contributors

import com.github.tifo.data.model.entity.contributors.ContributorsEntity

data class Contributors(
    val id: Int,
    val login: String,
    val avatarUrl: String
)

fun Contributors.toContributorsEntity(repositoryName: String): ContributorsEntity =
    ContributorsEntity(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        repositoryName = repositoryName
    )