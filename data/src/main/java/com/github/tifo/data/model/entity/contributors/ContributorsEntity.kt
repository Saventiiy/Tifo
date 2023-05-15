package com.github.tifo.data.model.entity.contributors

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.tifo.data.model.dto.contributors.Contributors

@Entity(tableName = "contributors_table")
data class ContributorsEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
    @ColumnInfo(name = "repository_name") val repositoryName: String
)

fun List<ContributorsEntity>.toContributors(): List<Contributors> =
    this.map {
        Contributors(
            id = it.id,
            login = it.login,
            avatarUrl = it.avatarUrl
        )
    }
