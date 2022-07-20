package com.aliens.ticketsapp.ui.screens.ticket


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.components.TicketItems
import com.aliens.ticketsapp.utils.Screen

@Composable
fun ConsultaTicketScreen(
    navController: NavController,
    viewModel: TicketViewModel = hiltViewModel()
) {

    Scaffold (
        topBar = {

            TopAppBar(
                title = {
                    Text(stringResource(R.string.Tickets))
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.RegistroTicket.route) }) {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.Agregar)
                        )
                    }
                }
            )

        }
    ){
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 64.dp)

        ) {

            val listaTecnicos = viewModel.tickets.collectAsState(initial = emptyList())

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(3) {
                    TicketItems(navController)
                }
            }


        }
    }

}

