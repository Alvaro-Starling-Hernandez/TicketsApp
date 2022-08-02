package com.aliens.ticketsapp.ui.screens.dashboard

import android.media.MediaDescription
import androidx.compose.ui.graphics.painter.Painter

data class InfoTicket(
    val title: String,
    val valor: String,
    val painter: Painter,
    val contentDescription: String,
    val height: Int,
    val width: Int
)

