package com.aliens.ticketsapp.ui.screens.tiempo

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.components.TiempoItem
import com.aliens.ticketsapp.utils.Screen

@Composable
fun ConsultaTiempoScreen(
    navController: NavController,
    viewModel: TiempoViewModel = hiltViewModel(),
) {
    var sum: Float = 0f
    val lista = viewModel.tiempos.collectAsState(initial = emptyList())
    lista.value.forEach {
        sum += it.tiempo
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
                                //navegar
                            }
                    )
                },
                title = {
                    Text(stringResource(R.string.TituloConsultaTiempos))
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.RegistroTiempo.route) }) {
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
                .padding(it),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(modifier = Modifier
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
                .height(600.dp)) {
                val listaTiempos = viewModel.tiempos.collectAsState(initial = emptyList())

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(listaTiempos.value) { tiempo ->
                        TiempoItem(tiempo, navController)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(end = 25.dp, bottom = 25.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End,

            ) {
                Text(sum.toString()
                    , fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
            }


        }
    }
}