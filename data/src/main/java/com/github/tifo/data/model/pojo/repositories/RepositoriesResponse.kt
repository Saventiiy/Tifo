package com.github.tifo.data.model.pojo.repositories

import com.github.tifo.data.model.dto.repositories.RepositoriesItem
import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(
    val items: List<RepositoriesItemResponse>
)

data class RepositoriesItemResponse(
    val id: Int,
    val name: String,
    @SerializedName("full_name") val fullName: String,
    val description: String?,
    val language: String?,
    @SerializedName("stargazers_count") val stars: Int
)

fun List<RepositoriesItemResponse>.toRepositoriesItemList(): List<RepositoriesItem> =
    this.map {
        RepositoriesItem(
            id = it.id,
            name = it.name,
            fullName = it.fullName,
            description = it.description,
            language = it.language,
            stars = it.stars
        )
    }
