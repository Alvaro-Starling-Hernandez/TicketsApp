package com.aliens.ticketsapp.ui.components.dashboardComps

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun InformacionClientes(total: Int) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Informacion de Clientes",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(10.dp)
        )
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 10.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                    Text(
                        text = "Clientes Totales ",
                        style = MaterialTheme.typography.h6,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.padding(top = 0.dp)
                    )
                    Text(
                        text = "$total",
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.End,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.padding(top = 0.dp).align(Alignment.End)
                    )
            }
        }
    }
}