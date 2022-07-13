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
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class RespuestaViewModel @Inject constructor(
    val respuestaRepository: RespuestaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var mensaje by mutableStateOf("")
    var fecha by mutableStateOf("")
    var id by mutableStateOf(0)
    var idTecnico by mutableStateOf(0)

    var respuestas = respuestaRepository.getList()
        private set

    //variables for search
    var List = mutableStateOf<List<Respuesta>>(listOf())
    private var cachedRespuestaList = listOf<Respuesta>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    fun searchRespuestaList(query: String) {
        val listToSearch = if(isSearchStarting) {
           List.value
        } else {
            cachedRespuestaList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                List.value = cachedRespuestaList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                 it.Mensaje.contains(query.trim(), ignoreCase = true) ||
                        it.Mensaje == query.trim()
            }
            if(isSearchStarting) {
                cachedRespuestaList = List.value
                isSearchStarting = false
            }
            List.value = results
            isSearching.value = true
        }
    }

    fun Guardar() {
        viewModelScope.launch {
            respuestaRepository.insertar(
                Respuesta(
                    Mensaje = mensaje,
                    fecha = fecha,
                    tecnicoId = idTecnico,
                    ticketId = 0,
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
}