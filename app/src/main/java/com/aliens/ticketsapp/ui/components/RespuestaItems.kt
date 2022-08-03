package com.aliens.ticketsapp.ui.components


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.model.Respuesta
import com.aliens.ticketsapp.model.Ticket
import com.aliens.ticketsapp.ui.screens.cliente.ClienteViewModel
import com.aliens.ticketsapp.ui.screens.respuesta.RespuestaViewModel
import com.aliens.ticketsapp.ui.screens.tecnico.TecnicoViewModel
import com.aliens.ticketsapp.ui.screens.ticket.TicketViewModel
import com.aliens.ticketsapp.utils.Screen
import com.aliens.ticketsapp.utils.getNombreTecnico
import kotlinx.coroutines.channels.ticker

@Composable
fun RespuestaItem(
    respuesta: Respuesta,
    navController: NavController
) {
    //var show by rememberSaveable { mutableStateOf(false) }
    //AlertDialog(show, {show = false}, {})
    var showDialog by rememberSaveable { mutableStateOf(false) }

    Modal(showDialog, { showDialog = false }, navController, respuesta.respuestaId, respuesta)

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        modifier = Modifier
            .padding(vertical = 5.dp)
            .padding(horizontal = 5.dp)
            .fillMaxWidth()
            .clickable { showDialog = true }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icons8_reply_all_48),
                    contentDescription = "ResponseIcon",
                    modifier = Modifier
                        .padding(8.dp)
                        .height(50.dp)
                        .width(50.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {

                    Text(
                        getNombreTecnico(respuesta.tecnicoId),
                        style = MaterialTheme.typography.h6,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        respuesta.Mensaje,
                        overflow = TextOverflow.Ellipsis,
                        fontStyle = FontStyle.Italic,
                        maxLines = 2
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    respuesta.fecha,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.padding(end = 15.dp)
                )
            }

        }

    }
}

@Composable
fun AlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Cancel")
                }
            },
            title = { Text(text = "Hola") },
            text = { Text(text = "Klk") },

            )
    }
}

//Custom
@Composable
fun Modal(
    show: Boolean,
    onDismiss: () -> Unit,
    navController: NavController,
    id: Int,
    respuesta: Respuesta,
    viewModel: RespuestaViewModel = hiltViewModel(),
    viewModel2: ClienteViewModel = hiltViewModel(),
    viewModel3: TicketViewModel = hiltViewModel()
) {
    val context = LocalContext.current
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
                                tint = colorResource(android.R.color.darker_gray),
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                                    .clickable { onDismiss() }
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                        Column {
                            Text(
                                "Tecnico: " + getNombreTecnico(respuesta.tecnicoId),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                            Text(
                                "Mensaje: " + respuesta.Mensaje,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row {
                            Button(
                                onClick = {
                                    navController.navigate(
                                        Screen.RegistroRespuesta.withArgs(
                                            id.toString(),
                                            respuesta.ticketId.toString()
                                        )
                                    )
                                    onDismiss()
                                },
                                modifier = Modifier.padding(start =70.dp),
                                shape = RoundedCornerShape(50.dp),
                                // modifier = Modifier.padding(start = 140.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.EditNote,
                                    contentDescription = stringResource(R.string.Guardar)
                                )
                                //Text("Editar")
                            }

                            var name: String = ""
                            var correo: String = ""
                            var idCliente:Int = 0
                            val clientes = viewModel2.clientes.collectAsState(initial = emptyList())
                            val listaTickets = viewModel3.buscar(respuesta.ticketId).collectAsState(initial = emptyList())
                            listaTickets.value.forEach {
                                idCliente = it.clienteId
                            }
                            Button(
                                onClick = {

                                    clientes.value.forEach {
                                        if (idCliente == it.clienteId) {
                                            name = it.nombreCliente
                                            correo = it.email
                                        }
                                    }
                                    share(receptor = correo, nombreCliente = name,cuerpo = respuesta.Mensaje, context)
                                    onDismiss()
                                },
                                modifier = Modifier.padding(start = 10.dp),
                                shape = RoundedCornerShape(50.dp),
                                //modifier = Modifier.padding(start = 140.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Compartir"
                                )
                                //Text("Editar")
                            }

                            Button(
                                onClick = {
                                    viewModel.eliminar(respuesta)
                                    onDismiss()
                                },
                                shape = RoundedCornerShape(50.dp),
                                modifier = Modifier.padding(start = 10.dp)
                                //modifier = Modifier.padding(start = 10.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = stringResource(R.string.Guardar)
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


fun share(
    receptor: String,
    nombreCliente: String,
    cuerpo: String,
    context: Context
) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse(
            "mailto:$receptor" +
                    "?cc=" + "" +
                    "&subject=" + Uri.encode("Saludos $nombreCliente, su ticket ha sido respondido") +
                    "&body=" + Uri.encode(cuerpo)
        )
    }
    val shareIntent = Intent.createChooser(intent, "Enviar al cliente")
    context.startActivity(shareIntent)
}

