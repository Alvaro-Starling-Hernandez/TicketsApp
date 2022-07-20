package com.aliens.ticketsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Prioridades")
data class Prioridad(
    @PrimaryKey(autoGenerate = true)
    val prioridadId: Int = 0,
    val descripcion: String
)