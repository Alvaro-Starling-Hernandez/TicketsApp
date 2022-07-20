package com.aliens.ticketsapp.data.repositories

import androidx.room.Insert
import com.aliens.ticketsapp.data.daos.PrioridadDao
import com.aliens.ticketsapp.model.Cliente
import com.aliens.ticketsapp.model.Prioridad
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrioridadRepository @Inject constructor(
    val prioridadDao: PrioridadDao
){
    suspend fun insertar(prioridad: Prioridad){
        prioridadDao.insertar(prioridad)
    }

    suspend fun eliminar(prioridad: Prioridad){
        prioridadDao.eliminar(prioridad)
    }

    fun buscar(prioridadId: Int): Flow<List<Prioridad>> {
        return prioridadDao.buscar(prioridadId)
    }

    fun getList(): Flow<List<Prioridad>> {
        return prioridadDao.getList()
    }
}