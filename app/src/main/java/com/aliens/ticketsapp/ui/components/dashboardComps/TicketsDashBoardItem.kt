package com.aliens.ticketsapp.ui.components.dashboardComps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aliens.ticketsapp.ui.screens.dashboard.InfoTicket

@Composable
fun TicketsDashBoardItem(infoTicket: InfoTicket) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(100.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = infoTicket.title,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(top = 1.dp)
            )
            Text(
                text = infoTicket.valor.toString(),
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(top = 35.dp)
                    .align(Alignment.End)
            )
        }
    }
}
