package com.example.qcells.installing

import com.example.qcells.model.InstallStatus
import com.example.qcells.repository.ApplicationRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

// using GlobalScope as default value because it is pretending to be a service in the background.
// OK for the purpose of simulating.
@OptIn(ExperimentalUuidApi::class, DelicateCoroutinesApi::class)
@Singleton
class InstallService @Inject constructor(
   val applicationRepository: ApplicationRepository,
   val scope: CoroutineScope = GlobalScope,
   ) {
   fun installApplication(uuid: Uuid) {
      scope.launch {
         applicationRepository.setInstallStatus(uuid.toHexString(), InstallStatus.INSTALLING)
         delay(Random.nextInt(from = 5000, until = 30000).toLong())
         applicationRepository.setInstallStatus(uuid.toHexString(), InstallStatus.INSTALLED)
      }
   }

   fun uninstallApplication(uuid: Uuid) {
      // using GlobalScope because it is pretending to be a service in the background. OK for the
      // purpose of simulating.
      scope.launch {
         applicationRepository.setInstallStatus(uuid.toHexString(), InstallStatus.UNINSTALLING)
         delay(Random.nextInt(from = 500, until = 10000).toLong())
         applicationRepository.setInstallStatus(uuid.toHexString(), InstallStatus.NOT_INSTALLED)
      }
   }
}
