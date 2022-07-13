package com.aliens.ticketsapp.ui.screens.respuesta

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
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
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.components.TecnicoSpinner
import com.aliens.ticketsapp.ui.components.TextObligatorio
import com.aliens.ticketsapp.ui.components.DateTimePicker
import com.aliens.ticketsapp.utils.Screen

@Composable
fun RegistroRespuestaScreen(
    navController: NavController,
    id: Int,
    viewModel: RespuestaViewModel = hiltViewModel()
) {
    var MensajeError by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    //val listaRespuestas = viewModel.buscar(id).collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = stringResource(R.string.ArrowBack),
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navController.navigate(Screen.ConsultaRespuesta.route)
                            }
                    )
                },
                title = { Text(stringResource(R.string.TituloResponder)) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
        ) {
            //Text(listaRespuestas.value.toString())
            TecnicoSpinner()

            Spacer(modifier = Modifier.height(25.dp))

            viewModel.id = id
            OutlinedTextField(
                value = viewModel.mensaje,
                onValueChange = {
                    viewModel.mensaje = it
                    MensajeError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                label = {
                    Text(stringResource(R.string.Mensaje))
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(bottom = 60.dp),
                        imageVector = Icons.Default.Mail,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text
                ),
                isError = MensajeError, maxLines = 4, singleLine = false
            )

            TextObligatorio(error = MensajeError)

            Spacer(modifier = Modifier.height(25.dp))

            DateTimePicker()

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    MensajeError = viewModel.mensaje.isBlank()
                    if (!MensajeError) {
                        viewModel.Guardar()
                        Toast.makeText(
                            context,
                            R.string.ToastMessageSave,
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(Screen.ConsultaRespuesta.route)
                    } else {
                        Toast.makeText(
                            context,
                            R.string.ToastMessageErrorAnswer,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = stringResource(R.string.Guardar)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = stringResource(R.string.Guardar))
            }
        }
    }
}
