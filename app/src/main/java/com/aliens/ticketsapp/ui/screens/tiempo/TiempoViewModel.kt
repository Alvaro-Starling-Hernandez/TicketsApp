package com.aliens.ticketsapp.ui.screens.tiempo

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliens.ticketsapp.data.repositories.TiempoRepository
import com.aliens.ticketsapp.model.Respuesta
import com.aliens.ticketsapp.model.Tiempo
import com.aliens.ticketsapp.ui.components.appBar.SearchWidgetState
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
    var idTicket by mutableStateOf(0)

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
                    ticketId = idTicket
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

    fun getTiempoByTicket(id: Int): Flow<List<Tiempo>> {
        return tiempoRepository.getTiempoByTicket(id)
    }

    fun isNumber(text: String): Boolean{
        return try {
            text.toFloat()
            true
        }catch (e: NumberFormatException){
            false
        }
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