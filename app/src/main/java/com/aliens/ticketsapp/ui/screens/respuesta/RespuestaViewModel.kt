package com.aliens.ticketsapp.ui.screens.respuesta

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliens.ticketsapp.data.repositories.RespuestaRepository
import com.aliens.ticketsapp.model.Respuesta
import com.aliens.ticketsapp.ui.components.appBar.SearchWidgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RespuestaViewModel @Inject constructor(
    val respuestaRepository: RespuestaRepository,
) : ViewModel() {
    var mensaje by mutableStateOf("")
    var fecha by mutableStateOf("")
    var id by mutableStateOf(0)
    var idTecnico by mutableStateOf(0)
    var idTicket by mutableStateOf(0)

    var respuestas = respuestaRepository.getList()
        private set

    fun Guardar() {
        viewModelScope.launch {
            respuestaRepository.insertar(
                Respuesta(
                    Mensaje = mensaje,
                    fecha = fecha,
                    tecnicoId = idTecnico,
                    ticketId = idTicket,
                    respuestaId = id
                )
            )
        }
    }

    fun eliminar(respuesta: Respuesta) {
        viewModelScope.launch {
            respuestaRepository.eliminar(respuesta)
        }
    }

    fun buscar(id: Int): Flow<List<Respuesta>> {
        return respuestaRepository.buscar(id)
    }

    fun getRespuestaByTicket(id: Int): Flow<List<Respuesta>> {
        return respuestaRepository.getRespuestaByTicket(id)
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