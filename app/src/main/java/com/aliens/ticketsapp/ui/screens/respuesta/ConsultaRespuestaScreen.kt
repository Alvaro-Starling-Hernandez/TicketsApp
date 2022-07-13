package com.aliens.ticketsapp.ui.screens.respuesta

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.components.RespuestaItem
import com.aliens.ticketsapp.utils.Screen

@Composable
fun ConsultaRespuestaScreen(
    navController: NavController,
    viewModel: RespuestaViewModel = hiltViewModel()
) {
    Scaffold(

        topBar = {

            TopAppBar(
                /*navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = stringResource(R.string.ArrowBack),
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                //navegar
                            }
                    )
                },*/
                title = {
                    Text(stringResource(R.string.Respuestas))
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.RegistroRespuesta.route) }) {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.Agregar)
                        )
                    }
                }
            )

        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp).height(650.dp)
        ) {

            val listaRespuestas = viewModel.respuestas.collectAsState(initial = emptyList())

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(listaRespuestas.value) { respuesta ->
                    RespuestaItem(respuesta, navController)
                }
            }

        }
    }
}
