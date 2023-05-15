package com.github.tifo.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.tifo.data.model.entity.contributors.ContributorsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContributorsDao {

    @Query("SELECT * FROM contributors_table WHERE repository_name =:repositoryName")
    fun getContributors(repositoryName: String): Flow<List<ContributorsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contributors: List<ContributorsEntity>)
}

