package com.aliens.ticketsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliens.ticketsapp.model.Ticket

@Composable
fun TicketItems(
    //ticket: Ticket,
    navController: NavController
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    //ModalTecnico(showDialog, { showDialog = false }, navController, tecnico.tecnicoId, tecnico)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { showDialog = true },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .height(70.dp)
                    .width(70.dp),
                shape = RoundedCornerShape(64.dp),
                backgroundColor = Color.Blue
            ) {
                Text(
                    text = InicalNombre("Prandi"), //getNombreCliente(ticket.clienteId)
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(6.dp)
                )
            }

            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                Text(
                    text = "Prandi Fabiel Cortorreal",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(10.dp))
                Row() {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "Asunto, Hay problems en esye proyecto",
                        style = MaterialTheme.typography.caption
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row() {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "En Proceso",
                        style = MaterialTheme.typography.caption

                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "18/7/2022",
                        style = MaterialTheme.typography.caption,
                        textAlign = TextAlign.End,
                        modifier = Modifier.padding(start = 75.dp)
                    )
                }
            }
        }
    }
}

