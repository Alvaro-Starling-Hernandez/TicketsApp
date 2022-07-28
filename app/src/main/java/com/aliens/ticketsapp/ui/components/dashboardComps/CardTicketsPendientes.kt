package com.aliens.ticketsapp.ui.components.dashboardComps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun CardTicketsPendientes(Pendientes: Int, Urgentes: Int, Alta: Int) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .padding(15.dp)
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = "Tickets pendientes: $Pendientes",
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(top = 15.dp)
            )
            Text(
                color = Color.Gray,
                text = "Urgentes: $Urgentes",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                color = Color.Gray,
                text = "Alta: $Alta",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}