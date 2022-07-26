package com.aliens.ticketsapp.ui.screens.respuesta

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.model.Respuesta
import com.aliens.ticketsapp.ui.components.RespuestaItem
import com.aliens.ticketsapp.ui.components.SearchView
import com.aliens.ticketsapp.ui.components.searchRespuesta.SearchAppBar
import com.aliens.ticketsapp.ui.components.searchRespuesta.SearchWidgetState
import com.aliens.ticketsapp.utils.Screen
import kotlinx.coroutines.flow.Flow

@Composable
fun ConsultaRespuestaScreen(
    navController: NavController,
    idTicket: Int,
    viewModel: RespuestaViewModel = hiltViewModel()
) {

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    val searchedText = textState.value.text

    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState

    Scaffold(

        topBar = {
            MainAppBar(
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
                    Log.d("Searched Text", it)
                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.RegistroRespuesta.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }, floatingActionButtonPosition = FabPosition.End

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
                .height(650.dp)
        ) {


            val listaRespuestas =
                viewModel.getRespuestaByTicket(idTicket).collectAsState(initial = emptyList())

            //SearchView(state = textState, placeHolder = "Buscar")
            LazyColumn() {
                items(items = listaRespuestas.value.filter { res ->
                    res.Mensaje.contains(searchTextState, ignoreCase = true) ||
                            res.fecha.contains(searchTextState, ignoreCase = true)
                }, key = { it.respuestaId }) { item ->
                    RespuestaItem(item, navController)
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
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = stringResource(R.string.ArrowBack),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(Screen.RegistroTicket.route)
                    }
            )
        },
        title = {
            Text(stringResource(R.string.Respuestas))
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
fun MainAppBar(
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
