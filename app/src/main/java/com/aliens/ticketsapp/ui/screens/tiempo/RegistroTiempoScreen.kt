package com.aliens.ticketsapp.ui.screens.tiempo

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.components.TecnicoSpinner
import com.aliens.ticketsapp.ui.components.TextObligatorio

@Composable
fun RegistroTiempoScreen(
    viewModel: TiempoViewModel = hiltViewModel()
) {
    var trabajoError by rememberSaveable { mutableStateOf(false) }
    var tiempoError by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = stringResource(R.string.ArrowBack),
                        modifier = Modifier.clickable {
                            //navegar
                        }
                    )
                },
                title = { Text(stringResource(R.string.TituloAdregarTiempo)) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
        ) {

            TecnicoSpinner()

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = viewModel.trabajo,
                onValueChange = {
                    viewModel.trabajo = it
                    trabajoError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.Trabajo))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Work,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text
                ),
                isError = trabajoError
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
                isError = tiempoError
            )

            TextObligatorio(error = tiempoError)

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    if(!trabajoError && !tiempoError) {
                        if (viewModel.isNumber(viewModel.tiempo) && viewModel.tiempo.toFloat() > 0) {
                            viewModel.Guardar()
                            Toast.makeText(
                                context,
                                "Guardado",
                                Toast.LENGTH_SHORT
                            ).show()
                            //navegar
                        } else {
                            Toast.makeText(
                                context,
                                "Entrada de tiempo invalida",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = stringResource(R.string.Guardar))
                Text(text = stringResource(R.string.Guardar))
            }

        }
    }
}