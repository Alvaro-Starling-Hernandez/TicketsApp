package com.aliens.ticketsapp.ui.screens.tecnico

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
import com.aliens.ticketsapp.ui.components.TecnicoItems
import com.aliens.ticketsapp.ui.components.appBar.SearchWidgetState
import com.aliens.ticketsapp.ui.components.appBar.AppBar
import com.aliens.ticketsapp.utils.Screen

@Composable
fun ConsultaTecnicoScreen(
    navController: NavController,
    viewModel: TecnicoViewModel = hiltViewModel()
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
                title = stringResource(R.string.Tecnicos)
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.RegistroTecnico.route)
                },
                modifier = Modifier.padding(bottom = 50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PersonAdd,
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

            val listaTecnicos = viewModel.tecnicos.collectAsState(initial = emptyList())

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = listaTecnicos.value.filter {res ->
                    res.nombreTecnico.contains(searchTextState, ignoreCase = true) ||
                            res.email.contains(searchTextState, ignoreCase = true) ||
                            res.telefonoTecnico.contains(searchTextState, ignoreCase = true)
                }, key = { it.tecnicoId }) { item ->
                    TecnicoItems(item, navController)
                }
            }


        }
    }

}