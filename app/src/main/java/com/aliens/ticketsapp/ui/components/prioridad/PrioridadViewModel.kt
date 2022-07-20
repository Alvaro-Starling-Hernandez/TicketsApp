package com.aliens.ticketsapp.ui.components.prioridad

import androidx.lifecycle.ViewModel
import com.aliens.ticketsapp.data.repositories.PrioridadRepository
import com.aliens.ticketsapp.model.Prioridad
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PrioridadViewModel @Inject constructor(
    val prioridadRepository: PrioridadRepository
): ViewModel() {

    var prioridades = prioridadRepository.getList()
        private set

    fun buscar(id: Int): Flow<List<Prioridad>> {
        return prioridadRepository.buscar(id)
    }
}