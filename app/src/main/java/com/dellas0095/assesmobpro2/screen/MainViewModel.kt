package com.dellas0095.assesmobpro2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dellas0095.assesmobpro2.database.Blushly.PelangganDao
import com.dellas0095.assesmobpro2.model.Pelanggan

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel (dao: PelangganDao) : ViewModel() {
    val data: StateFlow<List<Pelanggan>> = dao.getPelanggan().stateIn(

        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
}
