package com.dellas0095.assesmobpro2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dellas0095.assesmobpro2.database.Blushly.PelangganDao
import com.dellas0095.assesmobpro2.model.Pelanggan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


class DetailViewModel(private val dao: PelangganDao) : ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(nama: String, jumlah: String, variant: String) {
        val pelanggan = Pelanggan(
            nama = nama,
            jumlah = jumlah,
            variant = variant
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(pelanggan)
        }
    }

    suspend fun getPelanggan(id: Long): Pelanggan? {
        return dao.getPelangganById(id)
    }

    fun update(id: Long, nama: String, jumlah: String, variant: String) {
        val pelanggan = Pelanggan(
            id = id,
            nama = nama,
            jumlah = jumlah,
            variant = variant
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(pelanggan )
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)

        }
    }
}