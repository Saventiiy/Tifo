package com.github.tifo.data.model.dto.branches

import com.github.tifo.data.model.entity.branches.BranchesEntity

data class Branches(
    val name: String
)

fun Branches.toBranchesEntity(repositoryName: String): BranchesEntity =
    BranchesEntity(
        name = name,
        repositoryName = repositoryName
    )
