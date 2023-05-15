package com.github.tifo.screens.detailed.model

import com.github.tifo.data.model.dto.branches.Branches
import com.github.tifo.data.model.dto.contributors.Contributors

data class DetailedData(
    val branches: List<Branches>,
    val contributors: List<Contributors>,
    val name: String
)
