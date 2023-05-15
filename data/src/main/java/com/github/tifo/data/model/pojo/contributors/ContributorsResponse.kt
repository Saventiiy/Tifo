package com.github.tifo.data.model.pojo.contributors

import com.github.tifo.data.model.dto.contributors.Contributors
import com.google.gson.annotations.SerializedName

data class ContributorsResponse(
    val id: Int,
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)

fun ContributorsResponse.toContributors(): Contributors =
    Contributors(
        id = id,
        login = login,
        avatarUrl = avatarUrl
    )
