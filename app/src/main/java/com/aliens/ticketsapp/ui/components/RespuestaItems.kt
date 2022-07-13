package com.aliens.ticketsapp.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.model.Respuesta
import com.aliens.ticketsapp.ui.screens.respuesta.RespuestaViewModel
import com.aliens.ticketsapp.utils.Screen

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
            .padding(vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .clickable { showDialog = true }
            .padding(8.dp)) {
            Row(
            ) {
                Text("Nombre del Tecnico")
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    respuesta.fecha,
                    textAlign = TextAlign.End,
                    letterSpacing = 3.sp,
                    modifier = Modifier.padding(start = 60.dp)
                )

            }
            Column {
                Text(respuesta.Mensaje)
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
    viewModel: RespuestaViewModel = hiltViewModel()
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
                                    fontSize = 24.sp,
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

                        Row() {
                            Button(
                                onClick = {
                                    navController.navigate(Screen.RegistroRespuesta.withArgs(id.toString()))
                                    onDismiss()
                                },
                                shape = RoundedCornerShape(50.dp),
                                modifier = Modifier.padding(start = 140.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.EditNote,
                                    contentDescription = stringResource(R.string.Guardar)
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