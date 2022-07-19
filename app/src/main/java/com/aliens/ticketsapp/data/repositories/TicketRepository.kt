package com.aliens.ticketsapp.data.repositories

import com.aliens.ticketsapp.data.daos.TicketDao
import com.aliens.ticketsapp.model.Ticket
import com.aliens.ticketsapp.model.Tiempo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TicketRepository @Inject constructor(
    val ticketDao: TicketDao
) {
    suspend fun insertar(ticket: Ticket){
        ticketDao.insertar(ticket)
    }

    suspend fun eliminar(ticket: Ticket){
        ticketDao.eliminar(ticket)
    }

    fun buscar(ticketId: Int): Flow<List<Ticket>> {
        return ticketDao.buscar(ticketId)
    }

    fun getList(): Flow<List<Ticket>> {
        return ticketDao.getList()
    }
}