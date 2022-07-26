package com.aliens.ticketsapp.ui.screens.cliente

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.components.ClienteItem
import com.aliens.ticketsapp.ui.components.RespuestaItem
import com.aliens.ticketsapp.ui.components.TecnicoItems
import com.aliens.ticketsapp.ui.components.searchRespuesta.SearchAppBar
import com.aliens.ticketsapp.ui.components.searchRespuesta.SearchWidgetState
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
                }
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.RegistroCliente.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                )
            }
        }, floatingActionButtonPosition = FabPosition.End
    ){
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 64.dp)

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

@Composable
fun DefaultAppBar(
    navController: NavController,
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.Clientes))
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun AppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    navController: NavController,
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultAppBar(
                onSearchClicked = onSearchTriggered,
                navController = navController
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}