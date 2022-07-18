package com.aliens.ticketsapp.data.daos

import androidx.room.*
import com.aliens.ticketsapp.model.Cliente
import com.aliens.ticketsapp.model.Respuesta
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(cliente: Cliente)

    @Delete
    suspend fun eliminar(cliente: Cliente)

    @Query("SELECT * FROM clientes WHERE clienteId=:clienteId")
    fun buscar(clienteId: Int): Flow<List<Cliente>>

    @Query("SELECT * FROM clientes ORDER BY clienteId")
    fun getList(): Flow<List<Cliente>>
}