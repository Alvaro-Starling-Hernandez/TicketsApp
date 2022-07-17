package com.aliens.ticketsapp.ui.screens.tecnico

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliens.ticketsapp.data.repositories.TecnicoRepository
import com.aliens.ticketsapp.model.Tecnico
import com.aliens.ticketsapp.model.Tiempo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TecnicoViewModel @Inject constructor(
    val tecnicoRepository: TecnicoRepository
): ViewModel() {
    var nombreTecnico by mutableStateOf("")
    var telefonoTecnico by mutableStateOf("")
    var email by mutableStateOf("")

    var tecnicos = tecnicoRepository.getList()
        private set

    fun Guardar(){
        viewModelScope.launch {
           tecnicoRepository.insertar(
                Tecnico(
                    tecnicoId = 0,
                    nombreTecnico = nombreTecnico,
                    telefonoTecnico = telefonoTecnico,
                    email = email
                )
            )
        }
    }

    fun buscar(id: Int): Flow<List<Tecnico>> {
        return tecnicoRepository.buscar(id)
    }

    fun eliminar(tecnico: Tecnico){
        viewModelScope.launch {
            tecnicoRepository.eliminar(tecnico)
        }
    }
}