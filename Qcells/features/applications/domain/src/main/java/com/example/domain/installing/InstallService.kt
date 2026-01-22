package com.example.domain.installing

import kotlin.uuid.Uuid

// using GlobalScope as default value because it is pretending to be a service in the background.
// OK for the purpose of simulating.

interface InstallService {
   fun installApplication(uuid: Uuid)
   fun uninstallApplication(uuid: Uuid)
}
