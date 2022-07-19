package com.aliens.ticketsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tickets")
data class Ticket(
    @PrimaryKey(autoGenerate = true)
    val ticketId: Int = 0,
    val fecha: String,
    val asunto: String,
    val requerimiento: String,
    val clienteId: Int,
    val prioridadId: Int,
    val estadoId: Int
)