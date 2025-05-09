package com.dellas0095.assesmobpro2.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dellas0095.assesmobpro2.database.Blushly.PelangganDb
import com.dellas0095.assesmobpro2.ui.screen.DetailViewModel
import com.dellas0095.assesmobpro2.ui.screen.MainViewModel

class ViewModelFactory (
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("unhecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = PelangganDb.getInstance(context).dao
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}