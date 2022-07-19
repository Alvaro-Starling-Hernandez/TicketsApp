package com.aliens.ticketsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aliens.ticketsapp.data.daos.*
import com.aliens.ticketsapp.model.*

@Database(
    entities = [
        Tiempo::class,
        Respuesta::class,
        Tecnico::class,
        Cliente::class,
        Ticket::class
    ],
    exportSchema = false,
    version = 5
)
abstract class TicketsDb : RoomDatabase() {
    abstract val tiempoDao: TiempoDao
    abstract val respuestaDao: RespuestaDao
    abstract val tecnicoDao: TecnicoDao
    abstract val clienteDao: ClienteDao
    abstract val ticketDao: TicketDao
}