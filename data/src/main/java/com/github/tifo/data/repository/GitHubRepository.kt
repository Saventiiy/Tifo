package com.github.tifo.data.repository

import com.github.tifo.data.model.dto.branches.Branches
import com.github.tifo.data.model.dto.branches.toBranchesEntity
import com.github.tifo.data.model.dto.contributors.Contributors
import com.github.tifo.data.model.dto.contributors.toContributorsEntity
import com.github.tifo.data.model.dto.repositories.RepositoriesItem
import com.github.tifo.data.model.dto.repositories.toRepositoriesEntity
import com.github.tifo.data.model.entity.branches.toBranches
import com.github.tifo.data.model.entity.contributors.toContributors
import com.github.tifo.data.model.entity.repositories.toRepositoriesItem
import com.github.tifo.data.model.pojo.branches.toBranches
import com.github.tifo.data.model.pojo.contributors.toContributors
import com.github.tifo.data.model.pojo.repositories.toRepositoriesItemList
import com.github.tifo.data.source.local.dao.BranchesDao
import com.github.tifo.data.source.local.dao.ContributorsDao
import com.github.tifo.data.source.local.dao.RepositoriesDao
import com.github.tifo.data.source.remote.api.GitHubApi
import com.github.tifo.data.util.makeFullName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

open class GitHubRepository @Inject constructor(
    private val api: GitHubApi,
    private val repositoriesDao: RepositoriesDao,
    private val branchesDao: BranchesDao,
    private val contributorsDao: ContributorsDao
) : GitHubRepositoryInterface {

    private suspend fun saveRepositories(query: String) {
        try {
            api.searchRepositories(query).items.toRepositoriesItemList()
                .let { repositories ->
                    repositoriesDao.insert(repositories.map { it.toRepositoriesEntity() })
                }
        } catch (_: Exception) {
        }
    }

    override suspend fun getBranches(owner: String, repo: String) {
        api.getBranches(owner, repo)
            .map { it.toBranches() }
            .let { branches ->
                branchesDao.insert(branches.map {
                    it.toBranchesEntity(makeFullName(owner, repo))
                })
            }
    }

    override suspend fun getContributors(owner: String, repo: String) {
        api.getContributors(owner, repo)
            .map { it.toContributors() }
            .let { contributors ->
                contributorsDao.insert(contributors.map {
                    it.toContributorsEntity(makeFullName(owner, repo))
                })
            }
    }

    override fun getObservableRepositories(query: String): Flow<List<RepositoriesItem>> {
        return flowOf(query)
            .onStart { saveRepositories(query) }
            .flatMapConcat {
                if (query.isEmpty()) {
                    repositoriesDao.getAll().map { it.toRepositoriesItem() }
                } else {
                    repositoriesDao.getRepositories(query).map { it.toRepositoriesItem() }
                }
            }
    }

    override fun getObservableBranches(repositoryName: String): Flow<List<Branches>> {
        return branchesDao.getBranches(repositoryName).map { it.toBranches() }
    }

    override fun getObservableContributors(repositoryName: String): Flow<List<Contributors>> {
        return contributorsDao.getContributors(repositoryName).map { it.toContributors() }
    }
}