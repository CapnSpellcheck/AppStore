package com.example.qcells.ui.viewapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.qcells.model.iconColor
import com.example.qcells.model.rating
import com.example.qcells.ui.AppIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(viewModel: AppDetailsViewModel, navigateUpAction: () -> Unit) {
   val upIcon = @Composable {
      IconButton(onClick = navigateUpAction) {
         Icon(Icons.Default.ArrowBack, "navigate up")
      }
   }

   Scaffold(topBar = {
      TopAppBar(
         title = { },
         navigationIcon = upIcon
      )
   }) { paddingValues ->
      val applicationFlow = viewModel.applicationFlow.collectAsStateWithLifecycle()

      val modifier = Modifier
         .padding(paddingValues)
         .padding(horizontal = 16.dp)
         .verticalScroll(rememberScrollState())
      Column(modifier) {
         applicationFlow.value?.let { application ->
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
               AppIcon(application.iconColor, 80.dp)
               Text(application.name, style = MaterialTheme.typography.headlineLarge, fontWeight = Bold)
            }

            // Install / Uninstall
            if (viewModel.showInstallButton) {
               Button(
                  onClick = { viewModel.performInstallOrUninstall() },
                  enabled = viewModel.installActionEnabled,
                  modifier = Modifier
                     .padding(top = 20.dp)
                     .fillMaxWidth()
               ) {
                  Text(viewModel.installActionLabel)
               }
            }
            if (viewModel.showProgressIndicator) {
               CircularProgressIndicator(Modifier.align(CenterHorizontally))
            }

            Text("About this app", Modifier.padding(top = 40.dp), style = MaterialTheme.typography.labelMedium)
            Text(application.description, Modifier.padding(top = 4.dp))

            Text("Rating", Modifier.padding(top = 20.dp), style = MaterialTheme.typography.labelMedium)
            Text(application.rating ?: "No rating", Modifier.padding(top = 4.dp), style = MaterialTheme.typography.headlineMedium)

            Text("Developer", Modifier.padding(top = 20.dp), style = MaterialTheme.typography.labelMedium)
            Text(application.developerName, Modifier.padding(top = 4.dp))
         } ?: run {
            Text("Loading...")
         }
      }
   }
}

