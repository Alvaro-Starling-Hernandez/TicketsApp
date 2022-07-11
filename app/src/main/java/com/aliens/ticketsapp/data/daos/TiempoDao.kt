package com.aliens.ticketsapp.data.daos

import androidx.room.*
import com.aliens.ticketsapp.model.Tiempo
import kotlinx.coroutines.flow.Flow

@Dao
interface TiempoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(tiempo: Tiempo)

    @Delete
    suspend fun eliminar(tiempo: Tiempo)

    @Query("SELECT * FROM tiempos WHERE tiempoId =:tiempoId")
    fun buscar(tiempoId: Int): Flow<List<Tiempo>>

    @Query("SELECT * FROM tiempos ORDER BY tiempoId")
    fun getList(): Flow<List<Tiempo>>
}