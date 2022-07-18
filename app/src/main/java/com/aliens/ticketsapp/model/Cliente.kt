package com.aliens.ticketsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class Cliente (
    @PrimaryKey(autoGenerate = true)
    val clienteId: Int=0,
    val nombreCliente: String,
    val telefonoCliente: String,
    val email: String
)