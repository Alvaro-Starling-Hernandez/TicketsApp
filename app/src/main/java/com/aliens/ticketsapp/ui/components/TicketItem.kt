package com.aliens.ticketsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aliens.ticketsapp.model.Ticket
import com.aliens.ticketsapp.utils.getEstado
import com.aliens.ticketsapp.utils.getNombreCliente

@Composable
fun TicketItems(
    ticket: Ticket,
    navController: NavController
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    ModalTicket(showDialog, { showDialog = false }, navController, ticket.ticketId, ticket)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .clickable { showDialog = true },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.End,
        ) {
            Row(
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    tint = getColor(ticket.prioridadId),
                    modifier = Modifier.background(
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = getNombreCliente(ticket.clienteId),
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row() {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = ticket.asunto,
                    style = MaterialTheme.typography.caption,
                    fontSize = 18.sp
                )
            }
            Row() {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = ticket.requerimiento,
                    style = MaterialTheme.typography.caption,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row() {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = getEstado(ticket.estadoId),
                    style = MaterialTheme.typography.caption

                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = ticket.fecha,
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(start = 165.dp)
                )
            }
        }
    }
}

fun getColor(prioridad: Int): Color {
    var color = Color.Black
    when (prioridad) {
        1 -> color = Color.Red
        2 -> color = Color(color = 0xFFFF9800)
        3 -> color = Color.Yellow
        4 -> color = Color.Green
    }
    return color
}
