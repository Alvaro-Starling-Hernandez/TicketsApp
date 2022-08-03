package com.aliens.ticketsapp.ui.screens.ticket


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.components.TicketItems
import com.aliens.ticketsapp.ui.components.appBar.SearchWidgetState
import com.aliens.ticketsapp.ui.components.appBar.AppBar
import com.aliens.ticketsapp.ui.components.prioridad.PrioridadViewModel
import com.aliens.ticketsapp.ui.screens.cliente.ClienteViewModel
import com.aliens.ticketsapp.utils.Screen
import com.aliens.ticketsapp.utils.getEstado
import com.aliens.ticketsapp.utils.getNombreCliente

@Composable
fun ConsultaTicketScreen(
    navController: NavController,
    viewModel: TicketViewModel = hiltViewModel(),
    viewModel2: ClienteViewModel = hiltViewModel(),
    viewModel3: PrioridadViewModel = hiltViewModel()
) {

    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState

    Scaffold(
        topBar = {

            AppBar(
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
                title = stringResource(R.string.Tickets),
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.RegistroTicket.route)
                },
                modifier = Modifier.padding(bottom = 50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PostAdd,
                    contentDescription = null
                )
            }
        }, floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(5.dp, 5.dp, 5.dp, 64.dp)

        ) {

            val listaTickets = viewModel.tickets.collectAsState(initial = emptyList())
            var name: String = ""
            val clientes = viewModel2.clientes.collectAsState(initial = emptyList())
            var namePrio: String = ""
            val prioridades = viewModel3.prioridades.collectAsState(initial = emptyList())

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = listaTickets.value.filter { res ->
                    prioridades.value.forEach { element ->
                        if (res.prioridadId == element.prioridadId) {
                            namePrio = element.descripcion
                        }
                    }
                    clientes.value.forEach { element ->
                        if (res.clienteId == element.clienteId) {
                            name = element.nombreCliente
                        }
                    }
                    name.contains(
                        searchTextState,
                        ignoreCase = true
                    ) ||
                            res.asunto.contains(searchTextState, ignoreCase = true) ||
                            res.fecha.contains(searchTextState, ignoreCase = true) ||
                            res.requerimiento.contains(searchTextState, ignoreCase = true) ||
                            getEstado(res.estadoId).contains(searchTextState, ignoreCase = true) ||
                            namePrio.contains(searchTextState, ignoreCase = true)


                }, key = { it.ticketId }) { item ->
                    TicketItems(item, navController)
                }
            }


        }
    }

}



