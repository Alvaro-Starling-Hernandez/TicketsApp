package com.aliens.ticketsapp.ui.screens.dashboard

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.ui.components.dashboardComps.*
import com.aliens.ticketsapp.ui.screens.cliente.ClienteViewModel
import com.aliens.ticketsapp.ui.screens.ticket.TicketViewModel
import com.aliens.ticketsapp.ui.screens.tiempo.TiempoViewModel

@Composable
fun DashBoard(
    viewModel: TicketViewModel = hiltViewModel(),
    viewModelTiempo: TiempoViewModel = hiltViewModel(),
    viewModelCliente: ClienteViewModel = hiltViewModel()
) {

    //Variables contables
    var Pendientes = 0
    var Urgentes = 0
    var Finalizados = 0
    var EnProceso = 0
    var PrioridadAlta = 0

    val lista = viewModel.tickets.collectAsState(initial = emptyList())
    val listaClientes = viewModelCliente.clientes.collectAsState(initial = emptyList())

    //Obtener los valores contables
    var TicketsTotales = lista.value.size
    var clientesTotales = listaClientes.value.size
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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CardTicketsPendientes(Pendientes, Urgentes, PrioridadAlta)
                Progreso(size = TicketsTotales, finalizados = Finalizados)
            }

            InformacionClientes(clientesTotales)
            InformacionTickets(
                infoTicket = listOf(
                    InfoTicket(
                        title = "Totales",
                        valor = TicketsTotales.toString()
                    ),
                    InfoTicket(
                        title = "Finalizados",
                        valor = Finalizados.toString()
                    ),
                    InfoTicket(
                        title = "En Proceso",
                        valor = EnProceso.toString()
                    ),
                    InfoTicket(
                        title = "Tiempo Trabajado",
                        valor = "$sum Mins"
                    )
                )
            )
        }
    }
}

