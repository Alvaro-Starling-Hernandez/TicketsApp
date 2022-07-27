package com.aliens.ticketsapp.ui.screens.cliente

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.ui.components.TextObligatorio
import com.aliens.ticketsapp.ui.screens.tecnico.validarEmail
import com.aliens.ticketsapp.ui.screens.tecnico.validarPhone

@Composable
fun RegistroClienteScreen(
    navController: NavController,
    id: Int,
    viewModel: ClienteViewModel = hiltViewModel()
) {
    var nombreError by rememberSaveable { mutableStateOf(false) }
    var telefonoError by rememberSaveable { mutableStateOf(false) }
    var emailError by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    val listaClientes = viewModel.buscar(id).collectAsState(initial = emptyList())
    listaClientes.value.forEach {
        viewModel.nombre = it.nombreCliente
        viewModel.telefono = it.telefonoCliente
        viewModel.email = it.email
    }

    fun avisos(aviso: String) {
        Toast.makeText(
            context,
            aviso,
            Toast.LENGTH_SHORT
        ).show()
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
                                navController.navigateUp()
                            }
                    )
                },
                title = { Text("Clientes") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(25.dp))

            viewModel.idCiente = id
            OutlinedTextField(
                value = viewModel.nombre,
                onValueChange = {
                    viewModel.nombre= it
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
                isError = nombreError,
                singleLine = true
            )

            TextObligatorio(error = nombreError)

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = viewModel.telefono,
                onValueChange = {
                    viewModel.telefono = it
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
                isError = telefonoError,
                singleLine = true
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
                isError = emailError,
                singleLine = true
            )

            TextObligatorio(error = emailError)

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    nombreError = viewModel.nombre.isBlank()
                    telefonoError = viewModel.telefono.isBlank()
                    emailError = viewModel.email.isBlank()
                    if (!nombreError && !telefonoError && !emailError) {
                        if (validarPhone(viewModel.telefono)) {
                            if (validarEmail(viewModel.email)) {
                                viewModel.Guardar()
                                avisos("Guardado")
                                navController.navigateUp()

                            } else {
                                avisos("Email no valido")
                            }
                        } else {
                            avisos("Telefono no Valido")
                        }
                    } else {
                        avisos("Faltan Campos obligatorios")
                    }


                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = stringResource(com.aliens.ticketsapp.R.string.Guardar)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = stringResource(com.aliens.ticketsapp.R.string.Guardar))
            }

        }

    }

}