package com.aliens.ticketsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliens.ticketsapp.model.Cliente
import com.aliens.ticketsapp.utils.InicalNombre

@Composable
fun ClienteItem(
    cliente: Cliente,
    navController: NavController
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    ModalCliente(showDialog, { showDialog = false }, navController, cliente.clienteId, cliente)

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(vertical = 5.dp)
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
                    text = InicalNombre(cliente.nombreCliente),
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(6.dp),
                )
            }

            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                Text(
                    text = cliente.nombreCliente,
                    style = MaterialTheme.typography.h6,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row() {
                    Icon(Icons.Default.Email, contentDescription = null, tint = Color.Blue )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = cliente.email,
                        style = MaterialTheme.typography.caption,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row() {
                    Icon(Icons.Default.Phone, contentDescription = null, tint = Color.Blue)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = cliente.telefonoCliente,
                        style = MaterialTheme.typography.caption,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1

                    )
                }
            }


        }
    }
}