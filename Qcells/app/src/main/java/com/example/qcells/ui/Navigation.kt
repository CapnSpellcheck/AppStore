package com.example.qcells.ui

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi

object Navigation {
   @Serializable object AppList
   @Serializable object CreateApp
   @OptIn(ExperimentalUuidApi::class)
   @Serializable data class AppDetails(val appUuidHex: String)
}
