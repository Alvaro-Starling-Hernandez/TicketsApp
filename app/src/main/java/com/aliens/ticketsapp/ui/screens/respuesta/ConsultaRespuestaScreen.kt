package com.aliens.ticketsapp.ui.screens.respuesta

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
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
import com.aliens.ticketsapp.ui.components.appBar.AppBarConBackIcon
import com.aliens.ticketsapp.ui.components.appBar.SearchWidgetState
import com.aliens.ticketsapp.utils.Screen
import kotlinx.coroutines.flow.Flow

@Composable
fun ConsultaRespuestaScreen(
    navController: NavController,
    idTicket: Int,
    viewModel: RespuestaViewModel = hiltViewModel()
) {
    var auxId = 0

    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState

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
                title = stringResource(R.string.Respuestas)
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        Screen.RegistroRespuesta.withArgs(
                            auxId.toString(),
                            idTicket.toString()
                        )
                    )
                },
                modifier = Modifier.padding(bottom = 50.dp, end = 20.dp)
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

