package com.dellas0095.assesmobpro2.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dellas0095.assesmobpro2.database.Blushly.BlushlyDao
import com.dellas0095.assesmobpro2.model.Blushly
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn


class MainViewModel (dao: BlushlyDao) : ViewModel() {
    val data: StateFlow<List<Blushly>> = dao.getBlushly().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
}