package com.aliens.ticketsapp.ui.components.dashboardComps

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Saludo(
    name: String = "Tecnico"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Buen dia, $name",
                style = MaterialTheme.typography.h6
            )
            Text(
                color = Color.Gray,
                text = "Tenemos algunos tickets pendientes!",
                style = MaterialTheme.typography.body1
            )
        }
    }
}