package org.d3if3053.tukarin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3053.tukarin.model.KritikSaran


@Dao
interface KritikSaranDao {

    @Insert
    suspend fun insert(kritikSaran: KritikSaran)

    @Update
    suspend fun update(kritikSaran: KritikSaran)

    @Query("SELECT * FROM kritikSaran ORDER BY namamu DESC")
    fun getKritikSaran(): Flow<List<KritikSaran>>

    @Query("SELECT * FROM kritikSaran WHERE id = :id")
    suspend fun getKritikSaranById(id: Long): KritikSaran?

    @Query("DELETE FROM kritikSaran WHERE id = :id")
    suspend fun deleteById(id: Long)
}
