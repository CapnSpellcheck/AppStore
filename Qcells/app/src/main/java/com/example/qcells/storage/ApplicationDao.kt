package com.example.qcells.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.qcells.model.Application
import com.example.qcells.model.InstallStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao {
    @Query("SELECT * FROM Application")
    fun getAllAsFlow(): Flow<List<Application>>

    @Insert
    suspend fun create(application: Application)

    @Query("SELECT * FROM Application WHERE name LIKE '%' || :name || '%'")
    fun findByNameAsFlow(name: String): Flow<List<Application>>

    @Query("SELECT * FROM Application WHERE uuid = :uuid")
    fun getAsFlow(uuid: String): Flow<Application>

    @Query("UPDATE Application SET installStatus = :installStatus WHERE uuid = :uuid")
    suspend fun setInstallStatus(uuid: String, installStatus: InstallStatus)
}
