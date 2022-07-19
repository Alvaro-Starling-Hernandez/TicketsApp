package com.aliens.ticketsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aliens.ticketsapp.data.daos.ClienteDao
import com.aliens.ticketsapp.data.daos.RespuestaDao
import com.aliens.ticketsapp.data.daos.TecnicoDao
import com.aliens.ticketsapp.data.daos.TiempoDao
import com.aliens.ticketsapp.model.Cliente
import com.aliens.ticketsapp.model.Respuesta
import com.aliens.ticketsapp.model.Tecnico
import com.aliens.ticketsapp.model.Tiempo

@Database(
    entities = [
        Tiempo::class,
        Respuesta::class,
        Tecnico::class,
        Cliente::class
    ],
    exportSchema = false,
    version = 4
)
abstract class TicketsDb : RoomDatabase() {
    abstract val tiempoDao: TiempoDao
    abstract val respuestaDao: RespuestaDao
    abstract val tecnicoDao: TecnicoDao
    abstract val clienteDao: ClienteDao
}