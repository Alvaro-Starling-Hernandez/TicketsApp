package com.aliens.ticketsapp.ui.components

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.model.Ticket
import com.aliens.ticketsapp.model.Tiempo
import com.aliens.ticketsapp.ui.screens.ticket.TicketViewModel
import com.aliens.ticketsapp.ui.screens.tiempo.TiempoViewModel
import com.aliens.ticketsapp.utils.Screen
import com.aliens.ticketsapp.utils.getNombreCliente
import com.aliens.ticketsapp.utils.getNombreTecnico

@Composable
fun ModalTicket(
    show: Boolean,
    onDismiss: () -> Unit,
    navController: NavController,
    id: Int,
    ticket: Ticket,
    viewModel: TicketViewModel = hiltViewModel()
) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Opciones",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Icon(
                                imageVector = Icons.Filled.Cancel,
                                contentDescription = "",
                                tint = colorResource(R.color.darker_gray),
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                                    .clickable { onDismiss() }
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                        Column {
                            Text("Cliente: "+ getNombreCliente(cliente = ticket.clienteId))
                            Text("Asunto: "+ticket.asunto)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row {
                            Button(
                                onClick = {
                                    navController.navigate(Screen.RegistroTicket.withArgs(id.toString()))
                                    onDismiss()
                                },
                                shape = RoundedCornerShape(50.dp),
                                modifier = Modifier.padding(start = 140.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.EditNote,
                                    contentDescription = stringResource(com.aliens.ticketsapp.R.string.Guardar)
                                )
                                //Text("Editar")
                            }

                            Button(
                                onClick = {
                                    viewModel.eliminar(ticket)
                                    onDismiss()
                                },
                                shape = RoundedCornerShape(50.dp),
                                modifier = Modifier.padding(start = 10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = stringResource(com.aliens.ticketsapp.R.string.Guardar)
                                )
                                //Text("Eliminar")
                            }
                        }

                    }
                }
            }
        }
    }
}