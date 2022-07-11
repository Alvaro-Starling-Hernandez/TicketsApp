package com.aliens.ticketsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aliens.ticketsapp.data.daos.TiempoDao
import com.aliens.ticketsapp.model.Tiempo

@Database(
    entities = [Tiempo::class],
    exportSchema = false,
    version = 1
)
abstract class TicketsDb: RoomDatabase() {
    abstract val tiempoDao: TiempoDao
}