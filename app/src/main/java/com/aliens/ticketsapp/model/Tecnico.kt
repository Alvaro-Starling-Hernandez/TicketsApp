package com.aliens.ticketsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tecnicos")
data class Tecnico (
    @PrimaryKey(autoGenerate = true)
    val tecnicoId: Int=0,
    val nombreTecnico: String,
    val telefonoTecnico: String,
    val email: String
        )