package com.dellas0095.assesmobpro2.screen

import androidx.lifecycle.ViewModel
import com.dellas0095.assesmobpro2.model.Catatan

class MainViewModel : ViewModel() {

    val data = listOf(
        Catatan(
            1,
            "Nama Pembeli: ",
            "tanggal order",
            "jumlah order"

        ),
        Catatan(
            2,
            "Nama Pembeli:",
            "tanggal order",
            "jumlah order"

        ),
        Catatan(
            3,
            "Nama Pembeli: ",
            "tanggal order",
            "jumlah order"
        ),
        Catatan(
            4,
            "Nama Pembeli: ",
            "tanggal order",
            "jumlah order"

        ),
        Catatan(
            5,
            "Nama Pembeli:  ",
            "tanggal order",
            "jumlah order"

        ),
        Catatan(
            6,
            "Nama Pembeli: ",
            "tanggal order",
            "jumlah order"
        ),
        Catatan(
            7,
            "Nama Pembeli: ",
            "tanggal order",
            "jumlah order"

        )
    )
    fun getCatatan(id: Long):Catatan? {
        return data.find { it.id == id }
    }
}