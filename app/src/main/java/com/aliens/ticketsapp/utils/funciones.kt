package com.aliens.ticketsapp.utils

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.ui.components.prioridad.PrioridadViewModel
import com.aliens.ticketsapp.ui.screens.cliente.ClienteViewModel
import com.aliens.ticketsapp.ui.screens.tecnico.TecnicoViewModel
import com.aliens.ticketsapp.ui.screens.ticket.TicketViewModel

fun getEstado(id: Int): String {
    var str = ""

    when (id) {
        0 -> {
            str = "Pendiente"
        }
        1 -> {
            str = "En Proceso"
        }
        2 -> {
            str = "Finalizado"
        }
    }

    return str
}


@Composable
fun getNombreCliente(cliente: Int, viewModel: ClienteViewModel = hiltViewModel()): String {
    var name: String = ""
    val clientes = viewModel.clientes.collectAsState(initial = emptyList())

    clientes.value.forEach { element ->
        if (cliente == element.clienteId) {
            name = element.nombreCliente
        }
    }

    return name
}

@Composable
fun getNombreTecnico(tecn: Int, viewModel: TecnicoViewModel = hiltViewModel()): String {
    var name: String = "";
    val tecnicos = viewModel.tecnicos.collectAsState(initial = emptyList())

    tecnicos.value.forEach { element ->
        if (tecn == element.tecnicoId) {
            name = element.nombreTecnico
        }
    }

    return name
}

fun InicalNombre(inicial: String): String {
    var i = inicial[0]
    return i.toString().uppercase()
}

@Composable
fun getNombrePrioridad(prioridad: Int, viewModel: PrioridadViewModel = hiltViewModel()): String {
    var name: String = ""
    val prioridades = viewModel.prioridades.collectAsState(initial = emptyList())

    prioridades.value.forEach { element ->
        if (prioridad == element.prioridadId) {
            name = element.descripcion
        }
    }

    return name
}

fun getColorPrioridad(prioridad: Int): Color {
    var color = Color.Black
    when (prioridad) {
        1 -> color = Color.Red
        2 -> color = Color(color = 0xFFFF9800)
        3 -> color = Color.Yellow
        4 -> color = Color.Green
    }
    return color
}

fun getColorEstado(estado: Int): Color {
    var color = Color.Black
    when (estado) {
        0 -> color = Color(color = 0xFFFF9800)
        1 -> color = Color.Blue
        2 -> color = Color.Green
    }
    return color
}

@Composable
fun getSaludo(viewModel: TicketViewModel = hiltViewModel()): String {
    val tickets = viewModel.tickets.collectAsState(initial = emptyList())
    var ticketsResueltos by rememberSaveable { mutableStateOf(0) }
    tickets.value.forEach {
        if (it.estadoId != 2) {
            ticketsResueltos = 2
        } else {
            ticketsResueltos = 1
        }
    }
    if (tickets.value.isEmpty()) {
        ticketsResueltos = 3
    }
    var texto: String = when (ticketsResueltos) {
        1 -> "Todos los tickets estan finalizados, Buen trabajo!"
        2 -> "Tenemos algunos tickets pendientes!"
        else -> {
            "No hay tickets registrados para responder!"
        }
    }
    return texto
}

