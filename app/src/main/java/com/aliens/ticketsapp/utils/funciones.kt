package com.aliens.ticketsapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.ui.components.prioridad.PrioridadViewModel
import com.aliens.ticketsapp.ui.screens.cliente.ClienteViewModel
import com.aliens.ticketsapp.ui.screens.tecnico.TecnicoViewModel

fun getEstado(id: Int): String {
    var str: String = ""

    if (id == 0){
        str = "Pendiente"
    }else if(id == 1){
        str = "En Proceso"
    }else if(id == 2) {
        str = "Finalizado"
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
    return i.toString()
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
