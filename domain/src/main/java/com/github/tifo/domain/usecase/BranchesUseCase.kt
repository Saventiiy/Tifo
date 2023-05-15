package com.github.tifo.domain.usecase

import com.github.tifo.data.model.dto.request.Link
import com.github.tifo.data.repository.GitHubRepository
import javax.inject.Inject

class BranchesUseCase @Inject constructor(
    private val gitHubRepository: GitHubRepository,
) {

    suspend operator fun invoke(params: Link) {
        return gitHubRepository.getBranches(params.owner, params.repo)
    }
}
