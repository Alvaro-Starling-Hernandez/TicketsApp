package com.aliens.ticketsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Repuestas")
data class Respuesta(
    @PrimaryKey(autoGenerate = true)
    val respuestaId: Int = 0,
    val fecha: String,
    val ticketId: Int,
    val tecnicoId: Int,
    val Mensaje: String
)
