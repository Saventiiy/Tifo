package com.github.tifo.domain.usecase

import com.github.tifo.data.model.dto.repositories.RepositoriesItem
import com.github.tifo.data.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetObservableRepositoriesUseCase @Inject constructor(
    private val gitHubRepository: GitHubRepository,
) {

    operator fun invoke(params: String): Flow<List<RepositoriesItem>> {
        return gitHubRepository.getObservableRepositories(params)
    }
}
