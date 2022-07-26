package com.aliens.ticketsapp.ui.screens.cliente

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliens.ticketsapp.data.repositories.ClienteRepository
import com.aliens.ticketsapp.model.Cliente
import com.aliens.ticketsapp.ui.components.appBar.SearchWidgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    val clienteRepository: ClienteRepository
): ViewModel(){
    var nombre by mutableStateOf("")
    var telefono by mutableStateOf("")
    var email by mutableStateOf("")
    var idCiente by mutableStateOf(0)

    var clientes = clienteRepository.getList()
        private set

    fun Guardar() {
        viewModelScope.launch {
            clienteRepository.insertar(
                Cliente(
                    nombreCliente = nombre,
                    telefonoCliente = telefono,
                    email = email,
                    clienteId = idCiente
                )
            )
        }
    }

    fun eliminar(cliente: Cliente) {
        viewModelScope.launch {
            clienteRepository.eliminar(cliente)
        }
    }

    fun buscar(id: Int): Flow<List<Cliente>> {
        return clienteRepository.buscar(id)
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