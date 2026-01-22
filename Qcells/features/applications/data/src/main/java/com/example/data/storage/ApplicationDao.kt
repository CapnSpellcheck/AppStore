package com.example.qcells.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.qcells.model.Application
import com.example.qcells.model.InstallStatus
import com.example.qcells.repository.ApplicationRepository
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao : ApplicationRepository {
    @Query("SELECT * FROM Application")
    override fun getAllAsFlow(): Flow<List<Application>>

    @Insert
    override suspend fun create(application: Application)

    @Query("SELECT * FROM Application WHERE name LIKE '%' || :name || '%'")
    override fun findByNameAsFlow(name: String): Flow<List<Application>>

    @Query("SELECT * FROM Application WHERE uuid = :uuid")
    override fun getAsFlow(uuid: String): Flow<Application>

    @Query("UPDATE Application SET installStatus = :installStatus WHERE uuid = :uuid")
    override suspend fun setInstallStatus(uuid: String, installStatus: InstallStatus)
}
