package com.aliens.ticketsapp.ui.screens.respuesta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliens.ticketsapp.data.repositories.RespuestaRepository
import com.aliens.ticketsapp.model.Respuesta
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
}