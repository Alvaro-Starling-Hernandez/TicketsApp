package com.aliens.ticketsapp.ui.screens.tiempo

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
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.components.TecnicoSpinner
import com.aliens.ticketsapp.ui.components.TextObligatorio
import com.aliens.ticketsapp.ui.theme.TicketsAppTheme
import com.aliens.ticketsapp.utils.Screen

@Composable
fun RegistroTiempoScreen(
    navController: NavController,
    id: Int,
    idTicket: Int,
    viewModel: TiempoViewModel = hiltViewModel()
) {
    var trabajoError by rememberSaveable { mutableStateOf(false) }
    var tiempoError by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    val listaTiempos = viewModel.buscar(id).collectAsState(initial = emptyList())
    listaTiempos.value.forEach {
        viewModel.tecnicoId = it.tecnicoId
        viewModel.trabajo = it.trabajo
        viewModel.tiempo = it.tiempo.toString()
    }

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
                                navController.navigateUp()
                            }
                    )
                },
                title = { Text(stringResource(R.string.Tiempo)) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
        ) {

            TecnicoSpinner(idTec = viewModel.tecnicoId) //le puse 1 mientras tantos, debes hacer que se llenen los campos como hice yo

            Spacer(modifier = Modifier.height(25.dp))

            viewModel.id = id
            viewModel.idTicket = idTicket
             OutlinedTextField(
                value = viewModel.trabajo,
                onValueChange = {
                    viewModel.trabajo = it
                    trabajoError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                label = {
                    Text(stringResource(R.string.Trabajo))
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(bottom = 60.dp),
                        imageVector = Icons.Default.Work,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text
                ),
                isError = trabajoError, maxLines = 4, singleLine = false
            )

            TextObligatorio(error = trabajoError)

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = viewModel.tiempo,
                onValueChange = {
                    viewModel.tiempo = it
                    tiempoError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.Tiempo))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Decimal
                ),
                isError = tiempoError,
                singleLine = true
            )

            TextObligatorio(error = tiempoError)

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    trabajoError = viewModel.trabajo.isBlank()
                    tiempoError = viewModel.tiempo.isBlank()
                    if (!trabajoError && !tiempoError) {
                        if (viewModel.isNumber(viewModel.tiempo) && viewModel.tiempo.toFloat() > 0) {
                            viewModel.Guardar()
                            Toast.makeText(
                                context,
                                R.string.ToastMessageSave,
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigateUp()
                        } else {
                            Toast.makeText(
                                context,
                                "Entrada de tiempo invalida",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
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