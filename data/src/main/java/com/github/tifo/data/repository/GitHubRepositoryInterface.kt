package com.github.tifo.data.repository

import com.github.tifo.data.model.dto.branches.Branches
import com.github.tifo.data.model.dto.contributors.Contributors
import com.github.tifo.data.model.dto.repositories.RepositoriesItem
import kotlinx.coroutines.flow.Flow


interface GitHubRepositoryInterface {

    suspend fun getBranches(owner: String, repo: String)
    suspend fun getContributors(owner: String, repo: String)

    fun getObservableRepositories(query: String): Flow<List<RepositoriesItem>>
    fun getObservableBranches(repositoryName: String): Flow<List<Branches>>
    fun getObservableContributors(repositoryName: String): Flow<List<Contributors>>
}
