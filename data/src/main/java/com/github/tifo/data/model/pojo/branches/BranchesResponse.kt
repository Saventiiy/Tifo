package com.github.tifo.data.model.pojo.branches

import com.github.tifo.data.model.dto.branches.Branches

data class BranchesResponse(
    val name: String
)

fun BranchesResponse.toBranches(): Branches =
    Branches(
        name = name
    )

