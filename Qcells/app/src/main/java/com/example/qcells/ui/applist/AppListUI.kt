@file:OptIn(ExperimentalUuidApi::class)

package com.example.qcells.ui.applist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.qcells.model.*
import com.example.qcells.ui.AppIcon
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun AppListScreen(
   viewModel: AppListViewModel,
   addAppAction: () -> Unit,
   viewAppAction: (Uuid) -> Unit
) {
   val appsState = viewModel.filteredApplications.collectAsStateWithLifecycle()
   val query by viewModel.query.collectAsStateWithLifecycle()
   val hasLoadedData by viewModel.hasLoadedData.collectAsStateWithLifecycle()

   Scaffold(
      topBar = {
         TopAppBar(
            title = { Text("Apps") },
            actions = {
               TextButton(addAppAction) {
                  Text("Add App")
               }
            }
         )
      },
      bottomBar = {
         SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = query,
            onQueryChange = { viewModel.query.value = it },
            onSearch = {  },
            active = false,
            onActiveChange = {  },
            placeholder = { Text("Search apps") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") }
         ) {}
      }
   ) { paddingValues ->
      Box(Modifier.padding(paddingValues)) {
         LazyColumn(Modifier.fillMaxSize()) {
            items(appsState.value, key = { it.uuid }) { app ->
               AppRow(app, viewAppAction)
            }
         }
         if (appsState.value.isEmpty() && !hasLoadedData) {
            val reason = if (query.isEmpty()) "App Store is EMPTY!" else "No results"
            Text(reason, modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.headlineMedium)
         }
      }
   }
}

@Composable fun AppRow(application: Application, viewAppAction: (Uuid) -> Unit) {
   val modifier = Modifier
      .padding(horizontal = 16.dp, vertical = 10.dp)
      .fillMaxWidth()
      .clickable { viewAppAction(application.uuid) }
   Row(modifier) {
      AppIcon(application.iconColor, 60.dp)

      Column(Modifier.padding(start = 20.dp)) {
         Text(application.name)
         Text(application.category.description, Modifier.padding(vertical = 4.dp))
         Row {
            if (application.installStatus == InstallStatus.INSTALLED) {
               Text("\u2713 Installed", Modifier
                  .padding(end = 8.dp)
                  .background(Color(0.8f, 0.9f, 1.0f)))
            }
            if (application.ratingInTenths != null) {
               Text("${application.rating} \u2605")
            }
         }
      }
   }
}
