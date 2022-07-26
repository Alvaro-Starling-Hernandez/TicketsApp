package com.aliens.ticketsapp.data.repositories

import com.aliens.ticketsapp.data.daos.RespuestaDao
import com.aliens.ticketsapp.model.Respuesta
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RespuestaRepository @Inject constructor(
    val respuestaDao: RespuestaDao
){
    suspend fun insertar(respuesta: Respuesta){
        respuestaDao.insertar(respuesta)
    }

    suspend fun eliminar(respuesta: Respuesta){
        respuestaDao.eliminar(respuesta)
    }

    fun buscar(respuestaId: Int): Flow<List<Respuesta>> {
        return respuestaDao.buscar(respuestaId)
    }

    fun getRespuestaByTicket(ticketId: Int): Flow<List<Respuesta>> {
        return respuestaDao.getRespuestaByTicket(ticketId)
    }

    fun getList(): Flow<List<Respuesta>>{
        return respuestaDao.getList()
    }
}