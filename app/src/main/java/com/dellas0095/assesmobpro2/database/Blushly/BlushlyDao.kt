package com.dellas0095.assesmobpro2.database.Blushly

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dellas0095.assesmobpro2.model.Blushly
import kotlinx.coroutines.flow.Flow

@Dao
interface CatatanDao {

    @Insert
    suspend fun insert(catatan : Blushly)

    @Update
    suspend fun update(catatan: Blushly)

    @Query("SELECT * FROM catatan ORDER BY tanggal DESC")
    fun getCatatan(): Flow<List<Blushly>>

    @Query("SELECT * FROM catatan WHERE id = :id")
    suspend fun getCatatanById(id: Long): Blushly?

    @Query("DELETE FROM catatan WHERE id = :id")
    suspend fun deleteById(id: Long)
}