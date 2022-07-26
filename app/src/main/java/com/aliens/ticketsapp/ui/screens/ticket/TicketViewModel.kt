package com.aliens.ticketsapp.ui.screens.ticket

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliens.ticketsapp.data.repositories.TicketRepository
import com.aliens.ticketsapp.model.Ticket
import com.aliens.ticketsapp.ui.components.appBar.SearchWidgetState
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
                    prioridadId = prioridadId,
                    estadoId = estadoId
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

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}