package com.dellas0095.assesmobpro2.database.Blushly

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dellas0095.assesmobpro2.model.Pelanggan
import kotlinx.coroutines.flow.Flow

//@Dao
//interface PelangganDao {
//
//    @Insert
//    suspend fun insert(pelanggan: Pelanggan)
//
//    @Update
//    suspend fun update(pelanggan: Pelanggan)
//
//    @Query("SELECT * FROM pelanggan ORDER BY variant DESC")
//    fun getPelanggan(): Flow<List<Pelanggan>>
//
//    @Query("SELECT * FROM pelanggan WHERE id = :id")
//    suspend fun getPelangganById(id: Long): Pelanggan?
//
//    @Query("DELETE FROM pelanggan WHERE id = :id")
//    suspend fun deleteById(id: Long)
//}

@Dao
interface PelangganDao {
    @Insert suspend fun insert(pelanggan: Pelanggan)
    @Update suspend fun update(pelanggan: Pelanggan)

    @Query("SELECT * FROM pelanggan WHERE isDeleted = 0 ORDER BY variant ASC")
    fun getPelanggan(): Flow<List<Pelanggan>>

    @Query("SELECT * FROM pelanggan WHERE isDeleted = 1 ORDER BY variant ASC")
    fun getDeletedPelanggan(): Flow<List<Pelanggan>>

    @Query("SELECT * FROM pelanggan WHERE id = :id")
    suspend fun getPelangganById(id: Long): Pelanggan?

    @Query("DELETE FROM pelanggan WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE pelanggan SET isDeleted = 1 WHERE id = :id")
    suspend fun softDelete(id: Long)

    @Query("UPDATE pelanggan SET isDeleted = 0 WHERE id = :id")
    suspend fun restore(id: Long)
}