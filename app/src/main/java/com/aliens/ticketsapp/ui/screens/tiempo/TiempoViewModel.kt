package com.aliens.ticketsapp.ui.screens.tiempo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliens.ticketsapp.data.repositories.TiempoRepository
import com.aliens.ticketsapp.model.Respuesta
import com.aliens.ticketsapp.model.Tiempo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TiempoViewModel @Inject constructor(
    val tiempoRepository: TiempoRepository
): ViewModel() {
    var trabajo by mutableStateOf("")
    var tiempo by mutableStateOf("")
    var tecnicoId by mutableStateOf(0)
    var id by mutableStateOf(0)

    var tiempos = tiempoRepository.getList()
        private set

    fun Guardar() {
        viewModelScope.launch {
            tiempoRepository.insertar(
                Tiempo(
                    tiempoId = id,
                    trabajo = trabajo,
                    tiempo = tiempo.toFloat(),
                    tecnicoId = tecnicoId,
                    ticketId = 0
                )
            )
        }
    }

    fun eliminar(tiempo: Tiempo) {
        viewModelScope.launch {
            tiempoRepository.eliminar(tiempo)
        }
    }

    fun buscar(id: Int): Flow<List<Tiempo>> {
        return tiempoRepository.buscar(id)
    }

    fun isNumber(text: String): Boolean{
        return try {
            text.toFloat()
            true
        }catch (e: NumberFormatException){
            false
        }
    }
}