package com.aliens.ticketsapp.ui.screens.dashboard

import android.media.MediaDescription
import androidx.compose.ui.graphics.painter.Painter

data class InfoTicket(
    val title: String,
    val valor: String,
    var painter: Painter, // Imagen de fondo para la card
    var contentDescription: String, //Descripcion del painter
    var height: Int, //largo del painter
    var width: Int   //ancho del painter
)

