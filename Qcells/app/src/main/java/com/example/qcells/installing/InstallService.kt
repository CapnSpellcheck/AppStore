package com.example.qcells.installing

import com.example.qcells.storage.ApplicationDao
import com.example.qcells.model.InstallStatus
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class, DelicateCoroutinesApi::class)
@Singleton
class InstallService @Inject constructor(private val applicationDao: ApplicationDao) {
   fun installApplication(uuid: Uuid) {
      // using GlobalScope because it is pretending to be a service in the background. OK for the
      // purpose of simulating.
      GlobalScope.launch {
         applicationDao.setInstallStatus(uuid.toHexString(), InstallStatus.INSTALLING)
         delay(Random.nextInt(from = 5000, until = 30000).toLong())
         applicationDao.setInstallStatus(uuid.toHexString(), InstallStatus.INSTALLED)
      }
   }

   fun uninstallApplication(uuid: Uuid) {
      // using GlobalScope because it is pretending to be a service in the background. OK for the
      // purpose of simulating.
      GlobalScope.launch {
         applicationDao.setInstallStatus(uuid.toHexString(), InstallStatus.UNINSTALLING)
         delay(Random.nextInt(from = 500, until = 10000).toLong())
         applicationDao.setInstallStatus(uuid.toHexString(), InstallStatus.NOT_INSTALLED)
      }
   }
}
