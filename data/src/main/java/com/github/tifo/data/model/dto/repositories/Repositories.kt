package com.github.tifo.data.model.dto.repositories

import com.github.tifo.data.model.entity.repositories.RepositoriesEntity

data class RepositoriesItem(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    val language: String?,
    val stars: Int
)

fun RepositoriesItem.toRepositoriesEntity(): RepositoriesEntity =
    RepositoriesEntity(
        id = id,
        name = name,
        fullName = fullName,
        description = description,
        language = language,
        stars = stars
    )
