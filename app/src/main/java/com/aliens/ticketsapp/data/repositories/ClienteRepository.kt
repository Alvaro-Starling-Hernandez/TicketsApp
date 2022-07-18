package com.aliens.ticketsapp.data.repositories

import com.aliens.ticketsapp.data.daos.ClienteDao
import com.aliens.ticketsapp.model.Cliente
import com.aliens.ticketsapp.model.Tiempo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    val clienteDao: ClienteDao
) {
    suspend fun insertar(cliente: Cliente){
        clienteDao.insertar(cliente)
    }

    suspend fun eliminar(cliente: Cliente){
        clienteDao.eliminar(cliente)
    }

    fun buscar(clienteId: Int): Flow<List<Cliente>> {
        return clienteDao.buscar(clienteId)
    }

    fun getList(): Flow<List<Cliente>> {
        return clienteDao.getList()
    }
}