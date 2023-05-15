package com.github.tifo.domain.usecase

import com.github.tifo.data.model.dto.branches.Branches
import com.github.tifo.data.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetObservableBranchesUseCase @Inject constructor(
    private val gitHubRepository: GitHubRepository,
) {

    operator fun invoke(params: String): Flow<List<Branches>> {
        return gitHubRepository.getObservableBranches(params)
    }
}
