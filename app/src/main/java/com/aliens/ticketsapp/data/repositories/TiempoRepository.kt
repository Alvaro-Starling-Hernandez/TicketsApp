package com.aliens.ticketsapp.data.repositories

import com.aliens.ticketsapp.data.daos.TiempoDao
import com.aliens.ticketsapp.model.Tiempo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TiempoRepository @Inject constructor(
    val tiempoDao: TiempoDao
){
    suspend fun insertar(tiempo: Tiempo){
        tiempoDao.insertar(tiempo)
    }

    suspend fun eliminar(tiempo: Tiempo){
        tiempoDao.eliminar(tiempo)
    }

    fun buscar(tiempId: Int): Flow<List<Tiempo>> {
        return tiempoDao.buscar(tiempId)
    }

    fun getList(): Flow<List<Tiempo>>{
        return tiempoDao.getList()
    }
}