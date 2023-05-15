package com.github.tifo.data.model.entity.repositories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.tifo.data.model.dto.repositories.RepositoriesItem

@Entity(tableName = "repositories_table")
data class RepositoriesEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "language") val language: String?,
    @ColumnInfo(name = "stars") val stars: Int,
)

fun List<RepositoriesEntity>.toRepositoriesItem(): List<RepositoriesItem> =
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
