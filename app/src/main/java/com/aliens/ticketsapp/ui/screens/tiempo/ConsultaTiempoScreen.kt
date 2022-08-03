package com.aliens.ticketsapp.ui.screens.tiempo


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.MoreTime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.components.TiempoItem
import com.aliens.ticketsapp.ui.components.appBar.AppBarConBackIcon
import com.aliens.ticketsapp.ui.components.appBar.SearchWidgetState
import com.aliens.ticketsapp.utils.Screen

@Composable
fun ConsultaTiempoScreen(
    navController: NavController,
    idTicket: Int,
    viewModel: TiempoViewModel = hiltViewModel(),
) {

    val auxId = 0

    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState

    var sum = 0f
    val lista = viewModel.tiempos.collectAsState(initial = emptyList())
    lista.value.forEach {
        when(it.ticketId){
            idTicket -> sum += it.tiempo
        }
    }
    Scaffold(

        topBar = {

            AppBarConBackIcon(
                searchWidgetState = searchWidgetState,
                navController = navController,
                searchTextState = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {

                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                },
                navigateIcon = {
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
                title = stringResource(R.string.Tiempo)
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        Screen.RegistroTiempo.withArgs(
                            auxId.toString(),
                            idTicket.toString()
                        )
                    )
                },
                modifier = Modifier.padding(bottom = 50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreTime,
                    contentDescription = null
                )
            }
        }, floatingActionButtonPosition = FabPosition.End

    ) {
        Column(
            modifier = Modifier
                .padding(it),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
                    .height(600.dp)
            ) {
                val listaTiempos =
                    viewModel.getTiempoByTicket(idTicket).collectAsState(initial = emptyList())

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(items = listaTiempos.value.filter { res ->
                        res.tiempo.toString().contains(searchTextState, ignoreCase = true) ||
                                res.trabajo.contains(searchTextState, ignoreCase = true)
//                                getNombreTecnico(tecn = res.tecnicoId).contains(
//                                    searchTextState,
//                                    ignoreCase = true
//                                )
                    }, key = { it.tiempoId }) { item ->
                        TiempoItem(item, navController)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = 25.dp, bottom = 25.dp, end = 100.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,

                ) {
                Text(
                    text = "$sum mins",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
            }


        }
    }
}