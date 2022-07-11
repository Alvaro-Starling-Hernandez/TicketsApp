package com.aliens.ticketsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tiempos")
data class Tiempo(
    @PrimaryKey(autoGenerate = true)
    val tiempoId: Int = 0,
    val tecnicoId: Int,
    val ticketId: Int,
    val tiempo: Float,
    val trabajo: String
)