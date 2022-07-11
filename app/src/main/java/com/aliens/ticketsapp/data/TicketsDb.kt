package com.aliens.ticketsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aliens.ticketsapp.data.daos.RespuestaDao
import com.aliens.ticketsapp.data.daos.TiempoDao
import com.aliens.ticketsapp.model.Respuesta
import com.aliens.ticketsapp.model.Tiempo

@Database(
    entities = [Tiempo::class,
        Respuesta::class],
    exportSchema = false,
    version = 2
)
abstract class TicketsDb : RoomDatabase() {
    abstract val tiempoDao: TiempoDao
    abstract val respuestaDao: RespuestaDao
}