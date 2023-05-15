package com.github.tifo.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.tifo.data.model.entity.repositories.RepositoriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoriesDao {

    @Query("SELECT * FROM repositories_table")
    fun getAll(): Flow<List<RepositoriesEntity>>

    @Query("SELECT * FROM repositories_table WHERE full_name LIKE '%' || :query || '%' ")
    fun getRepositories(query: String): Flow<List<RepositoriesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repositories: List<RepositoriesEntity>)
}
