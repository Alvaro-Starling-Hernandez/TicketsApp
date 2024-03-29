package com.aliens.ticketsapp.ui.screens.cliente

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.components.ClienteItem
import com.aliens.ticketsapp.ui.components.appBar.SearchWidgetState
import com.aliens.ticketsapp.ui.components.appBar.AppBar
import com.aliens.ticketsapp.utils.Screen

@Composable
fun ConsultaClienteScreen(
    navController: NavController,
    viewModel: ClienteViewModel = hiltViewModel()
) {

    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState

    Scaffold (
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
                title = stringResource(R.string.Clientes)
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.RegistroCliente.route)
                }
                ,
                modifier = Modifier.padding(bottom = 50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PersonAdd,
                    contentDescription = null
                )
            }
        }, floatingActionButtonPosition = FabPosition.End
    ){
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(5.dp, 5.dp, 5.dp, 64.dp)

        ) {

            val listaClientes = viewModel.clientes.collectAsState(initial = emptyList())

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = listaClientes.value.filter {res ->
                    res.nombreCliente.contains(searchTextState, ignoreCase = true) ||
                            res.email.contains(searchTextState, ignoreCase = true) ||
                            res.telefonoCliente.contains(searchTextState, ignoreCase = true)
                }, key = { it.clienteId }) { item ->
                    ClienteItem(item, navController)
                }
            }


        }
    }
}