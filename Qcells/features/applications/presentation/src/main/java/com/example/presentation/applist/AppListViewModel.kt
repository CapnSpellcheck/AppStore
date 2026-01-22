package com.example.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qcells.model.Application
import com.example.qcells.repository.ApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(val applicationRepository: ApplicationRepository) : ViewModel() {
    var query = MutableStateFlow("")
    var hasLoadedData = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredApplications: StateFlow<List<Application>> = query.flatMapLatest { query ->
        val result = applicationRepository.findByNameAsFlow(query)
        hasLoadedData.value = true
        result
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}
