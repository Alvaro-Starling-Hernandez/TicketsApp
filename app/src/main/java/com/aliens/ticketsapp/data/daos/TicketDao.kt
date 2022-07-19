package com.aliens.ticketsapp.data.daos

import androidx.room.*
import com.aliens.ticketsapp.model.Ticket
import com.aliens.ticketsapp.model.Tiempo
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(ticket: Ticket)

    @Delete
    suspend fun eliminar(ticket: Ticket)

    @Query("SELECT * FROM tickets WHERE ticketId =:ticketId")
    fun buscar(ticketId: Int): Flow<List<Ticket>>

    @Query("SELECT * FROM tickets ORDER BY ticketId")
    fun getList(): Flow<List<Ticket>>
}