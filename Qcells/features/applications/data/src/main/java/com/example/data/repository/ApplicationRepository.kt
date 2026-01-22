package com.example.qcells.repository

import com.example.qcells.model.Application
import com.example.qcells.model.InstallStatus
import kotlinx.coroutines.flow.Flow

interface ApplicationRepository {
   fun getAllAsFlow(): Flow<List<Application>>

   suspend fun create(application: Application)

   fun findByNameAsFlow(name: String): Flow<List<Application>>

   fun getAsFlow(uuid: String): Flow<Application>

   suspend fun setInstallStatus(uuid: String, installStatus: InstallStatus)
}
