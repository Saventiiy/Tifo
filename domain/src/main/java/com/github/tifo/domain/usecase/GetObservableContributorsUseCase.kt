package com.github.tifo.domain.usecase

import com.github.tifo.data.model.dto.contributors.Contributors
import com.github.tifo.data.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetObservableContributorsUseCase @Inject constructor(
    private val gitHubRepository: GitHubRepository,
) {

    operator fun invoke(params: String): Flow<List<Contributors>> {
        return gitHubRepository.getObservableContributors(params)
    }
}
