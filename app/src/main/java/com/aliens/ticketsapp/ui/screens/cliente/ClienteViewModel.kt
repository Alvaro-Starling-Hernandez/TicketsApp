package com.aliens.ticketsapp.ui.screens.cliente

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliens.ticketsapp.data.repositories.ClienteRepository
import com.aliens.ticketsapp.model.Cliente
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
}