package com.aliens.ticketsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.screens.respuesta.RespuestaViewModel
import com.aliens.ticketsapp.ui.screens.tecnico.TecnicoViewModel
import com.aliens.ticketsapp.ui.screens.tiempo.TiempoViewModel

@Composable
fun TecnicoSpinner(
    idTec: Int,
    viewModel: TecnicoViewModel = hiltViewModel(),
    respuestaViewModel: RespuestaViewModel = hiltViewModel(),
    tiempoViewModel: TiempoViewModel = hiltViewModel()
) {

    var writeNameTecnico: String

    writeNameTecnico = getNombreTecnico(idTec)

    var mExpanded by remember { mutableStateOf(false) }

    val tecnicos = viewModel.tecnicos.collectAsState(initial = emptyList())

    var mSelectedText by remember {
        mutableStateOf(writeNameTecnico)
    }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(
        Modifier
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            value = writeNameTecnico,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .clickable { }
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text(stringResource(R.string.Tecnico)) },
            trailingIcon = {
                Icon(
                    icon,
                    stringResource(R.string.Tecnico),
                    Modifier.clickable { mExpanded = !mExpanded }
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            },
            readOnly = true
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            tecnicos.value.forEach {
                DropdownMenuItem(onClick = {
                    respuestaViewModel.idTecnico = it.tecnicoId
                    tiempoViewModel.tecnicoId = it.tecnicoId
                    mSelectedText = it.nombreTecnico
                    mExpanded = false
                }) {
                    Text(text = it.nombreTecnico)
                }
            }
        }
    }
}