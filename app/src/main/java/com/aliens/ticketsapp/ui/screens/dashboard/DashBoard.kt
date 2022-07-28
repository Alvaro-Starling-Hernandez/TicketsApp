package com.aliens.ticketsapp.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.ui.components.dashboardComps.CardTicketsPendientes
import com.aliens.ticketsapp.ui.components.dashboardComps.InformacionClientes
import com.aliens.ticketsapp.ui.components.dashboardComps.InformacionTickets
import com.aliens.ticketsapp.ui.components.dashboardComps.Saludo
import com.aliens.ticketsapp.ui.screens.ticket.TicketViewModel
import com.aliens.ticketsapp.ui.screens.tiempo.TiempoViewModel

@Composable
fun DashBoard(
    viewModel: TicketViewModel = hiltViewModel(),
    viewModelTiempo: TiempoViewModel = hiltViewModel()
) {

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

    var sum = 0f
    val listaTiempo = viewModelTiempo.tiempos.collectAsState(initial = emptyList())
    listaTiempo.value.forEach {
        sum += it.tiempo
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Saludo()
            CardTicketsPendientes(Pendientes, Urgentes, PrioridadAlta)
            InformacionClientes(total = 4)
            InformacionTickets(
                infoTicket = listOf(
                    InfoTicket(
                        title = "Finalizados",
                        valor = Finalizados.toString()
                    ),
                    InfoTicket(
                        title = "Tickets",
                        valor = TicketsTotales.toString()
                    ),
                    InfoTicket(
                        title = "En Proceso",
                        valor = EnProceso.toString()
                    ),
                    InfoTicket(
                        title = "Trabajado",
                        valor = "$sum Mins"
                    )
                )
            )
        }
    }
}

