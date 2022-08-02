package com.aliens.ticketsapp.ui.components.dashboardComps

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aliens.ticketsapp.ui.screens.dashboard.InfoTicket
import com.aliens.ticketsapp.R

@Composable
fun InformacionTickets(infoTicket: List<InfoTicket>) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Informacion de Tickets",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(10.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(infoTicket.size) { item ->
                TicketsDashBoardItem(infoTicket[item])
            }
        }
    }
}