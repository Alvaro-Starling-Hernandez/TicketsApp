package com.aliens.ticketsapp.ui.components.dashboardComps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aliens.ticketsapp.R

@Composable
fun CardTicketsPendientes(Pendientes: Int, Urgentes: Int, Alta: Int) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .padding(15.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.fondo_naranja),
            contentDescription = "Fondo Naranja",
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(250.dp)
                .height(100.dp)
        )

        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = "Tickets pendientes: $Pendientes",
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(top = 0.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                color = Color.White,
                text = "Urgentes: $Urgentes",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
            )
            Text(
                color = Color.White,
                text = "Alta: $Alta",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}