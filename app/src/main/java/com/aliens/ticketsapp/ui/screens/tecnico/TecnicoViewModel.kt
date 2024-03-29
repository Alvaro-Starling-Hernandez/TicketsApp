package com.aliens.ticketsapp.ui.screens.tecnico

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliens.ticketsapp.data.repositories.TecnicoRepository
import com.aliens.ticketsapp.model.Tecnico
import com.aliens.ticketsapp.ui.components.appBar.SearchWidgetState
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
    var id by mutableStateOf(0)

    var tecnicos = tecnicoRepository.getList()
        private set

    fun Guardar(){
        viewModelScope.launch {
           tecnicoRepository.insertar(
                Tecnico(
                    tecnicoId = id,
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