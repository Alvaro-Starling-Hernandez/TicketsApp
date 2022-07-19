package com.aliens.ticketsapp.ui.screens.ticket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliens.ticketsapp.data.repositories.TicketRepository
import com.aliens.ticketsapp.model.Ticket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    val ticketRepository: TicketRepository
): ViewModel(){
    var fecha by mutableStateOf("")
    var asunto by mutableStateOf("")
    var requerimiento by mutableStateOf("")
    var clienteId by mutableStateOf(0)
    var prioridadId by mutableStateOf(0)
    var estadoId by mutableStateOf(0)
    var id by mutableStateOf(0)

    var tickets = ticketRepository.getList()
        private set

    fun Guardar() {
        viewModelScope.launch {
            ticketRepository.insertar(
                Ticket(
                    ticketId = id,
                    fecha = fecha,
                    asunto = asunto,
                    requerimiento = requerimiento,
                    clienteId = clienteId,
                    prioridadId = 0,
                    estadoId = 0
                )
            )
        }
    }

    fun eliminar(ticket: Ticket) {
        viewModelScope.launch {
            ticketRepository.eliminar(ticket)
        }
    }

    fun buscar(id: Int): Flow<List<Ticket>> {
        return ticketRepository.buscar(id)
    }
}