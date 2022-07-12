package com.aliens.ticketsapp.ui.screens.tecnico

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
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
import com.aliens.ticketsapp.ui.components.TextObligatorio

@Composable
fun TecnicoScreen(
    viewModel: TecnicoViewModel = hiltViewModel(),
    navController: NavController
) {
    var nombreError by rememberSaveable { mutableStateOf(false) }
    var telefonoError by rememberSaveable { mutableStateOf(false) }
    var emailError by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

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
                                //navegar
                            }
                    )
                },
                title = { Text("Tecnicos") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
        ) {

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = viewModel.nombreTecnico,
                onValueChange = {
                    viewModel.nombreTecnico = it
                    nombreError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Nombre")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                },
                isError = nombreError
            )

            TextObligatorio(error = nombreError)

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = viewModel.telefonoTecnico,
                onValueChange = {
                    viewModel.telefonoTecnico = it
                    telefonoError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Telefono")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                isError = telefonoError
            )

            TextObligatorio(error = telefonoError)

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = viewModel.email,
                onValueChange = {
                    viewModel.email = it
                    emailError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Email")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                isError = emailError
            )

            TextObligatorio(error = emailError)

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    nombreError = viewModel.nombreTecnico.isBlank()
                    telefonoError = viewModel.telefonoTecnico.isBlank()
                    emailError = viewModel.email.isBlank()
                    if (!nombreError && !telefonoError && !emailError) {
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
                            "Faltan Campos obligatorios",
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

