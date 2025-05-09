package com.dellas0095.assesmobpro2.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dellas0095.assesmobpro2.database.Blushly.PelangganDao
import com.dellas0095.assesmobpro2.model.Pelanggan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TrashViewModel(private val dao: PelangganDao) : ViewModel() {

    val data: StateFlow<List<Pelanggan>> = dao.getDeletedPelanggan().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun pulihkan(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.restore(id)
        }
    }

    fun hapusPermanen(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}