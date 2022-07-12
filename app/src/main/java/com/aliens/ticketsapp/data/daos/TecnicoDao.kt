package com.aliens.ticketsapp.data.daos

import androidx.room.*
import com.aliens.ticketsapp.model.Respuesta
import com.aliens.ticketsapp.model.Tecnico
import kotlinx.coroutines.flow.Flow

@Dao
interface TecnicoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(tecnico: Tecnico)

    @Delete
    suspend fun eliminar(tecnico: Tecnico)

   @Query("SELECT * FROM tecnicos WHERE tecnicoId=:tecnicoId")
    fun buscar(tecnicoId: Int): Flow<List<Tecnico>>

    @Query("SELECT * FROM tecnicos ORDER BY tecnicoId")
    fun getList(): Flow<List<Tecnico>>
}