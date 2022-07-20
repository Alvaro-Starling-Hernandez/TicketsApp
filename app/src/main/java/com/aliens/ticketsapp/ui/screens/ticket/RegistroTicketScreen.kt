package com.aliens.ticketsapp.ui.screens.ticket

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.ui.components.*
import com.aliens.ticketsapp.utils.Screen

@Composable
fun RegistroTicketScreen(
    navController: NavController,
    id: Int,
    viewModel: TicketViewModel = hiltViewModel(),
) {
    var asuntoError by rememberSaveable { mutableStateOf(false) }
    var requerimientoError by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    val listaTickets = viewModel.buscar(id).collectAsState(initial = emptyList())
    listaTickets.value.forEach {
        viewModel.clienteId = it.clienteId
        viewModel.fecha = it.fecha
        viewModel.asunto = it.asunto
        viewModel.requerimiento = it.requerimiento
    }


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = stringResource(com.aliens.ticketsapp.R.string.ArrowBack),
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                //navController.navigate(Screen.ConsultaTiempo.route)
                            }
                    )
                },
                title = { Text(stringResource(com.aliens.ticketsapp.R.string.Tickets)) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.size(60.dp)) {
                Icon(imageVector = Icons.Default.Save, contentDescription = null, modifier = Modifier.size(40.dp))
            }
        }, floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
        ) {

            ClienteSpinner(idCliente = viewModel.clienteId)

            Spacer(modifier = Modifier.height(15.dp))

            DateTimePickerTicket()

            Spacer(modifier = Modifier.height(15.dp))

            viewModel.id = id
            OutlinedTextField(
                value = viewModel.asunto,
                onValueChange = {
                    viewModel.asunto = it
                    asuntoError = false
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(stringResource(com.aliens.ticketsapp.R.string.Asunto))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Subject,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text
                ),
                isError = asuntoError
            )

            TextObligatorio(error = asuntoError)

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = viewModel.requerimiento,
                onValueChange = {
                    viewModel.requerimiento = it
                    requerimientoError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                label = {
                    Text(stringResource(com.aliens.ticketsapp.R.string.Requerimiento))
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(bottom = 60.dp),
                        imageVector = Icons.Default.Assignment,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Decimal
                ),
                isError = requerimientoError, maxLines = 4, singleLine = false
            )

            TextObligatorio(error = requerimientoError)

            Spacer(modifier = Modifier.height(15.dp))

            ClienteSpinner(idCliente = viewModel.clienteId)

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(com.aliens.ticketsapp.R.string.Responder))
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        imageVector = Icons.Default.Reply,
                        contentDescription = stringResource(com.aliens.ticketsapp.R.string.Guardar)
                    )
                }

                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(com.aliens.ticketsapp.R.string.AgregarTiempo))
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        imageVector = Icons.Default.MoreTime,
                        contentDescription = stringResource(com.aliens.ticketsapp.R.string.Guardar)
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Finalizar")
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = stringResource(com.aliens.ticketsapp.R.string.Guardar)
                )
            }
//            Button(
//                onClick = {
////                    trabajoError = viewModel.trabajo.isBlank()
////                    tiempoError = viewModel.tiempo.isBlank()
////                    if (!trabajoError && !tiempoError) {
////                        if (viewModel.isNumber(viewModel.tiempo) && viewModel.tiempo.toFloat() > 0) {
////                            viewModel.Guardar()
////                            Toast.makeText(
////                                context,
////                                com.aliens.ticketsapp.R.string.ToastMessageSave,
////                                Toast.LENGTH_SHORT
////                            ).show()
////                            navController.navigate(Screen.ConsultaTiempo.route)
////                        } else {
////                            Toast.makeText(
////                                context,
////                                "Entrada de tiempo invalida",
////                                Toast.LENGTH_SHORT
////                            ).show()
////                        }
////                    }
//                },
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .fillMaxWidth()
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Save,
//                    contentDescription = stringResource(com.aliens.ticketsapp.R.string.Guardar)
//                )
//                Spacer(modifier = Modifier.width(5.dp))
//                Text(text = stringResource(com.aliens.ticketsapp.R.string.Guardar))
//            }

        }
    }
}


















