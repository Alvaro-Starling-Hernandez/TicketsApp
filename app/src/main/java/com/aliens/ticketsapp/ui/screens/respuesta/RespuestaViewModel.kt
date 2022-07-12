package com.aliens.ticketsapp.ui.screens.respuesta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliens.ticketsapp.data.repositories.RespuestaRepository
import com.aliens.ticketsapp.model.Respuesta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RespuestaViewModel @Inject constructor(
    val respuestaRepository: RespuestaRepository
) : ViewModel() {
    var mensaje by mutableStateOf("")
    var fecha by mutableStateOf("")

    var respuestas = respuestaRepository.getList()
        private set

    fun Guardar() {
        viewModelScope.launch {
            respuestaRepository.insertar(
                Respuesta(
                    Mensaje = mensaje,
                    fecha = fecha,
                    tecnicoId = 0,
                    ticketId = 0
                )
            )
        }
    }

}