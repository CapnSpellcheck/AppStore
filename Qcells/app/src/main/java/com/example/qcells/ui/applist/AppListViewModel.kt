package com.example.qcells.ui.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qcells.model.Application
import com.example.qcells.storage.ApplicationDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(val applicationDao: ApplicationDao) : ViewModel() {
    var query = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredApplications: StateFlow<List<Application>> = query.flatMapLatest { query ->
        applicationDao.findByNameAsFlow(query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}
