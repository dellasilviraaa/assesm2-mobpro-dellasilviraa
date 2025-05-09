package com.dellas0095.assesmobpro2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dellas0095.assesmobpro2.database.Blushly.BlushlyDao
import com.dellas0095.assesmobpro2.model.Blushly

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DetailViewModel(private val dao: BlushlyDao) : ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(judul: String, isi: String) {
        val catatan = Blushly(
            tanggal = formatter.format(Date()),
            judul = judul,
            catatan = isi
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(catatan)
        }
    }

    suspend fun getCatatan(id: Long): Blushly? {
        return dao.getBlushlyById(id)
    }

    fun update(id: Long, judul: String, isi: String) {
        val catatan = Blushly(
            id = id,
            tanggal = formatter.format(Date()),
            judul = judul,
            catatan = isi
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(catatan)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)

        }
    }
}