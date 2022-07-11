package com.aliens.ticketsapp.data.daos

import androidx.room.*
import com.aliens.ticketsapp.model.Respuesta
import kotlinx.coroutines.flow.Flow

@Dao
interface RespuestaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(respuesta: Respuesta)

    @Delete
    suspend fun eliminar(respuesta: Respuesta)

    @Query("SELECT * FROM repuestas WHERE respuestaId=:respuestaId")
    fun buscar(respuestaId: Int): Flow<List<Respuesta>>

    @Query("SELECT * FROM repuestas ORDER BY respuestaId")
    fun getList(): Flow<List<Respuesta>>
}