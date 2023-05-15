package com.github.tifo.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.tifo.data.model.entity.branches.BranchesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BranchesDao {

    @Query("SELECT * FROM branches_table WHERE repository_name =:repositoryName")
    fun getBranches(repositoryName: String): Flow<List<BranchesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(branches: List<BranchesEntity>)
}