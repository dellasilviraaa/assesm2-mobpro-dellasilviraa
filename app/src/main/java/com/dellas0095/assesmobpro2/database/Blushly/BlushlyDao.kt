package com.dellas0095.assesmobpro2.database.Blushly

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dellas0095.assesmobpro2.model.Blushly
import kotlinx.coroutines.flow.Flow

@Dao
interface BlushlyDao {

    @Insert
    suspend fun insert(blushly : Blushly)

    @Update
    suspend fun update(blushly: Blushly)

    @Query("SELECT * FROM blushly ORDER BY tanggal DESC")
    fun getBlushly(): Flow<List<Blushly>>

    @Query("SELECT * FROM blushly WHERE id = :id")
    suspend fun getBlushlyById(id: Long): Blushly?

    @Query("DELETE FROM blushly WHERE id = :id")
    suspend fun deleteById(id: Long)
}