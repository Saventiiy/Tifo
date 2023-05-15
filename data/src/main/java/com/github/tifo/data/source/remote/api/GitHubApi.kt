package com.github.tifo.data.source.remote.api

import com.github.tifo.data.model.pojo.branches.BranchesResponse
import com.github.tifo.data.model.pojo.contributors.ContributorsResponse
import com.github.tifo.data.model.pojo.repositories.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    //    https://api.github.com/search/repositories?q=Moya&per_page=100
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
    ): RepositoriesResponse

    //    https://api.github.com/repos/Moya/Moya/branches
    @GET("repos/{owner}/{repo}/branches")
    suspend fun getBranches(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<BranchesResponse>

    //   https://api.github.com/repos/Moya/Moya/contributors
    @GET("repos/{owner}/{repo}/contributors")
    suspend fun getContributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<ContributorsResponse>
}

