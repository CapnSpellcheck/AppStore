@file:OptIn(ExperimentalUuidApi::class)

package com.example.qcells.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.presentation.applist.AppListScreen
import com.example.presentation.newapp.CreateAppScreen
import com.example.presentation.viewapp.AppDetailsScreen
import com.example.presentation.viewapp.AppDetailsViewModel
import com.example.qcells.ui.theme.QcellsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.uuid.ExperimentalUuidApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         AppUI()
      }
   }
}

@Composable fun AppUI() {
   val navController = rememberNavController()

   QcellsTheme {
      NavHost(navController, startDestination = Navigation.AppList) {
         composable<Navigation.AppList> { backStackEntry ->
            AppListScreen(
               hiltViewModel(),
               addAppAction = { navController.navigate(Navigation.CreateApp) },
               viewAppAction = { navController.navigate(Navigation.AppDetails(it.toHexString())) }
            )
         }
         composable<Navigation.CreateApp> {
            CreateAppScreen(hiltViewModel()) {
               navController.popBackStack()
            }
         }
         composable<Navigation.AppDetails> { backStackEntry ->
            val appUuidHex = backStackEntry.toRoute<Navigation.AppDetails>().appUuidHex
            AppDetailsScreen(hiltViewModel<AppDetailsViewModel, AppDetailsViewModel.Factory>(creationCallback = { it.create(appUuidHex) })) {
               navController.navigateUp()
            }
         }

      }
   }
}
