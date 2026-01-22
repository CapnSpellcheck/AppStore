package com.example.presentation.viewapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.installing.InstallService
import com.example.qcells.model.Application
import com.example.qcells.model.InstallStatus
import com.example.qcells.repository.ApplicationRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlin.uuid.ExperimentalUuidApi

private const val LOG_TAG = "AppDetailsViewModel"

@OptIn(ExperimentalUuidApi::class)
@HiltViewModel(assistedFactory = AppDetailsViewModel.Factory::class)
class AppDetailsViewModel @AssistedInject constructor(
   val applicationRepository: ApplicationRepository,
   val installService: InstallService,
   @Assisted val appUuidHex: String
) : ViewModel() {
   val applicationFlow: StateFlow<Application?> = applicationRepository.getAsFlow(appUuidHex)
      .stateIn(
         scope = viewModelScope,
         started = SharingStarted.WhileSubscribed(5000),
         initialValue = null
      )

   val installActionEnabled: Boolean
      get() = applicationFlow.value?.installStatus in listOf(InstallStatus.INSTALLED, InstallStatus.NOT_INSTALLED)

   val installActionLabel: String
      get() = when (applicationFlow.value?.installStatus) {
         InstallStatus.INSTALLING, InstallStatus.NOT_INSTALLED -> "Install"
         InstallStatus.UNINSTALLING, InstallStatus.INSTALLED -> "Uninstall"
         else -> ""
      }

   val showInstallButton: Boolean
      get() = when (applicationFlow.value?.installStatus) {
         InstallStatus.NOT_COMPATIBLE, null -> false
         else -> true
      }

   val showProgressIndicator: Boolean
      get() = applicationFlow.value?.installStatus in listOf(InstallStatus.INSTALLING, InstallStatus.UNINSTALLING)

   fun performInstallOrUninstall() {
      when (applicationFlow.value?.installStatus) {
         InstallStatus.NOT_INSTALLED ->
            installService.installApplication(applicationFlow.value!!.uuid)
         InstallStatus.INSTALLED ->
            installService.uninstallApplication(applicationFlow.value!!.uuid)
         InstallStatus.UNINSTALLING, InstallStatus.INSTALLING, InstallStatus.NOT_COMPATIBLE ->
            Log.e(LOG_TAG, "performUninstallOrUninstall: can't install or uninstall in this status (${applicationFlow.value?.installStatus})")
         null ->
            Log.e(LOG_TAG, "performUninstallOrUninstall: app is not loaded")
      }
   }

   @AssistedFactory interface Factory {
      fun create(appUuidHex: String): AppDetailsViewModel
   }
}

