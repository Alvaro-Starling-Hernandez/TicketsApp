package com.aliens.ticketsapp.ui.screens.dashboard

import android.media.MediaDescription
import androidx.compose.ui.graphics.painter.Painter

data class InfoTicket(
    val title: String,
    val valor: String,
    val painter: Painter, // Imagen de fondo para la card
    val contentDescription: String, //Descripcion del painter
    val height: Int, //largo del painter
    val width: Int   //ancho del painter
)

