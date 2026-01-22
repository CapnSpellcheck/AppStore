package com.example.presentation.newapp

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qcells.model.Application
import com.example.qcells.model.ApplicationCategory
import com.example.qcells.model.InstallStatus
import com.example.qcells.repository.ApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@HiltViewModel
class CreateAppViewModel @Inject constructor(val applicationRepository: ApplicationRepository) : ViewModel() {
   val events = MutableSharedFlow<Event>(extraBufferCapacity = 1)

   fun createApp(name: String, developerName: String, category: ApplicationCategory, rating: Int?, description: String) {
      // validate
      if (name.isBlank()) {
         events.tryEmit(Event.InvalidInput("Name cannot be blank"))
         return
      }
      if (developerName.isBlank()) {
         events.tryEmit(Event.InvalidInput("Developer name cannot be blank"))
         return
      }

      viewModelScope.launch {
         // generate icon color
         val colorInt = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()).toArgb()
         val app = Application(
            uuid = Uuid.random(),
            name = name,
            category = category,
            ratingInTenths = rating,
            developerName = developerName,
            installStatus = InstallStatus.NOT_INSTALLED,
            iconColorAsInt = colorInt,
            description = description,
         )
         applicationRepository.create(app)
         events.tryEmit(Event.AppCreated)
      }
   }

   sealed class Event {
      object AppCreated : Event()
      class InvalidInput(val message: String) : Event()
   }
}
