package com.github.tifo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.tifo.data.BuildConfig
import com.github.tifo.data.model.entity.branches.BranchesEntity
import com.github.tifo.data.model.entity.contributors.ContributorsEntity
import com.github.tifo.data.model.entity.repositories.RepositoriesEntity
import com.github.tifo.data.source.local.dao.BranchesDao
import com.github.tifo.data.source.local.dao.ContributorsDao
import com.github.tifo.data.source.local.dao.RepositoriesDao

@Database(
    entities = [
        RepositoriesEntity::class,
        BranchesEntity::class,
        ContributorsEntity::class,
    ],
    version = BuildConfig.DATABASE_VERSION
)
abstract class Database : RoomDatabase() {
    abstract fun repositoriesDao(): RepositoriesDao
    abstract fun branchesDao(): BranchesDao
    abstract fun contributorsDao(): ContributorsDao
}
