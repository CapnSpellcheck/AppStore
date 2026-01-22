package com.example.presentation.newapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.qcells.model.ApplicationCategory
import com.example.qcells.model.formatRating
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable fun CreateAppScreen(viewModel: CreateAppViewModel, appCreatedAction: () -> Unit,) {
   var errorMessage: String? by rememberSaveable { mutableStateOf(null) }

   LaunchedEffect(Unit) {
      launch {
         viewModel.events.collect { event ->
            when (event) {
               CreateAppViewModel.Event.AppCreated -> appCreatedAction()
               is CreateAppViewModel.Event.InvalidInput -> {
                  errorMessage = event.message
               }
            }
         }
      }
   }

   var name by rememberSaveable { mutableStateOf("") }
   var developerName by rememberSaveable { mutableStateOf("") }
   var description by rememberSaveable { mutableStateOf("") }
   var ratingInTenths: Int? by rememberSaveable { mutableStateOf(null) }
   var applicationCategory by rememberSaveable { mutableStateOf(ApplicationCategory.ART_AND_DESIGN) }

   var submitEnabled by rememberSaveable { mutableStateOf(true) }
   var ratingExpanded by rememberSaveable { mutableStateOf(false) }
   var categoryExpanded by rememberSaveable { mutableStateOf(false) }

   Scaffold(topBar = {
      TopAppBar(
         title = { Text("Create an app") },
      )
   }) { paddingValues ->
      val modifier = Modifier.padding(paddingValues).padding(horizontal = 16.dp)
      Column(modifier, verticalArrangement = Arrangement.spacedBy(20.dp)) {
         TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("App name") },
            modifier = Modifier.align(Alignment.End),
            maxLines = 1,
         )
         TextField(
            value = developerName,
            onValueChange = { developerName = it },
            placeholder = { Text("Developer name") },
            modifier = Modifier.align(Alignment.End),
            maxLines = 1,
            )
         TextField(
            value = description,
            onValueChange = { description = it },
            placeholder = { Text("Description") },
            modifier = Modifier.align(Alignment.End).height(120.dp)
         )

         Row {
            Text("Rating")
            Spacer(Modifier.weight(1f))
            ExposedDropdownMenuBox(
               expanded = ratingExpanded,
               onExpandedChange = { ratingExpanded = !ratingExpanded }
            ) {
               TextField(
                  value = ratingInTenths?.let { formatRating(it) } ?: "No rating",
                  onValueChange = {},
                  readOnly = true,
                  trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = ratingExpanded) },
                  modifier = Modifier.menuAnchor()
               )
               ExposedDropdownMenu(
                  expanded = ratingExpanded,
                  onDismissRequest = { ratingExpanded = false }
               ) {
                  DropdownMenuItem(
                     text = { Text("No rating") },
                     onClick = {
                        ratingExpanded = false
                        ratingInTenths = null
                     }
                  )
                  (10..50).forEach {
                     DropdownMenuItem(
                        text = { Text(formatRating(it)) },
                        onClick = {
                           ratingInTenths = it
                           ratingExpanded = false
                        }
                     )
                  }
               }
            }
         }

         Row {
            Text("Category")
            Spacer(Modifier.weight(1f))
            ExposedDropdownMenuBox(
               expanded = categoryExpanded,
               onExpandedChange = { categoryExpanded = !categoryExpanded }
            ) {
               TextField(
                  value = applicationCategory.description,
                  onValueChange = {},
                  readOnly = true,
                  trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                  modifier = Modifier.menuAnchor()
               )
               ExposedDropdownMenu(
                  expanded = categoryExpanded,
                  onDismissRequest = { categoryExpanded = false }
               ) {
                  ApplicationCategory.entries.forEach {
                     DropdownMenuItem(
                        text = { Text(it.description) },
                        onClick = {
                           applicationCategory = it
                           categoryExpanded = false
                        }
                     )
                  }
               }
            }
         }

         Button(enabled = submitEnabled, modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
            submitEnabled = false
            viewModel.createApp(name, developerName, applicationCategory, ratingInTenths, description)
         }) {
            Text("Create")
         }
      }

      errorMessage?.let { error ->
         val dismiss = { errorMessage = null; submitEnabled = true }
         AlertDialog(
            onDismissRequest = dismiss,
            confirmButton = { TextButton(dismiss) { Text("OK")} },
            title = { Text("Can't create app") },
            text = { Text(error) }
         )
      }
   }
}
