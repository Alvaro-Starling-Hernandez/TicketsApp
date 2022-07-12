package com.aliens.ticketsapp.data.repositories

import com.aliens.ticketsapp.data.daos.TecnicoDao
import com.aliens.ticketsapp.model.Tecnico
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TecnicoRepository @Inject constructor(
    val tecnicoDao: TecnicoDao
) {
    suspend fun insertar(tecnico: Tecnico){
       tecnicoDao.insertar(tecnico)
    }

    suspend fun eliminar(tecnico: Tecnico){
        tecnicoDao.eliminar(tecnico)
    }

    fun buscar(tecnicoId: Int): Flow<List<Tecnico>> {
        return tecnicoDao.buscar(tecnicoId)
    }

    fun getList(): Flow<List<Tecnico>> {
        return tecnicoDao.getList()
    }
}