package com.github.tifo.data.model.entity.branches

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.tifo.data.model.dto.branches.Branches

@Entity(tableName = "branches_table")
data class BranchesEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "repository_name") val repositoryName: String
)

fun List<BranchesEntity>.toBranches(): List<Branches> =
    this.map {
        Branches(name = it.name)
    }
