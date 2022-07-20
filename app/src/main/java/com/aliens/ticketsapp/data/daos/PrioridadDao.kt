package com.aliens.ticketsapp.data.daos

import androidx.room.*
import com.aliens.ticketsapp.model.Prioridad
import com.aliens.ticketsapp.model.Tiempo
import kotlinx.coroutines.flow.Flow

@Dao
interface PrioridadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(prioridad: Prioridad)

    @Delete
    suspend fun eliminar(prioridad: Prioridad)

    @Query("SELECT * FROM prioridades WHERE prioridadId =:prioridadId")
    fun buscar(prioridadId: Int): Flow<List<Prioridad>>

    @Query("SELECT * FROM prioridades ORDER BY prioridadId")
    fun getList(): Flow<List<Prioridad>>
}