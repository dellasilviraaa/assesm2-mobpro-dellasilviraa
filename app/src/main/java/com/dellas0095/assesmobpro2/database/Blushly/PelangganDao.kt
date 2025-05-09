package com.dellas0095.assesmobpro2.database.Blushly

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dellas0095.assesmobpro2.model.Pelanggan
import kotlinx.coroutines.flow.Flow

@Dao
interface PelangganDao {

    @Insert
    suspend fun insert(pelanggan: Pelanggan)

    @Update
    suspend fun update(pelanggan: Pelanggan)

    @Query("SELECT * FROM pelanggan ORDER BY variant DESC")
    fun getPelanggan(): Flow<List<Pelanggan>>

    @Query("SELECT * FROM pelanggan WHERE id = :id")
    suspend fun getPelangganById(id: Long): Pelanggan?

    @Query("DELETE FROM pelanggan WHERE id = :id")
    suspend fun deleteById(id: Long)
}