package com.aliens.ticketsapp.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.ui.components.dashboardComps.CardTicketsPendientes
import com.aliens.ticketsapp.ui.components.dashboardComps.InformacionTickets
import com.aliens.ticketsapp.ui.components.dashboardComps.Saludo
import com.aliens.ticketsapp.ui.screens.ticket.TicketViewModel

@Composable
fun DashBoard(viewModel: TicketViewModel = hiltViewModel()) {

    //Variables contables
    var Pendientes = 0
    var Urgentes = 0
    var Finalizados = 0
    var EnProceso = 0
    var PrioridadAlta = 0

    val lista = viewModel.tickets.collectAsState(initial = emptyList())

    //Obtener los valores contables
    var TicketsTotales = lista.value.size
    lista.value.forEach {
        when (it.estadoId) {
            0 -> Pendientes++
            1 -> EnProceso++
            2 -> Finalizados++
        }
        if (it.prioridadId == 2 && it.estadoId != 2) {
            PrioridadAlta++
        }
        if (it.prioridadId == 1 && it.estadoId != 2) {
            Urgentes++
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Saludo()
            CardTicketsPendientes(Pendientes, Urgentes, PrioridadAlta)
            Spacer(modifier = Modifier.height(30.dp))
            InformacionTickets(
                infoTicket = listOf(
                    InfoTicket(
                        title = "Finalizados",
                        valor = Finalizados
                    ),
                    InfoTicket(
                        title = "Tickets",
                        valor = TicketsTotales
                    ),
                    InfoTicket(
                        title = "En Proceso",
                        valor = EnProceso
                    )
                )
            )
        }
    }
}

