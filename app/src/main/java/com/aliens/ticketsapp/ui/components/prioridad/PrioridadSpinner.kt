package com.aliens.ticketsapp.ui.components.prioridad

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.screens.cliente.ClienteViewModel
import com.aliens.ticketsapp.ui.screens.ticket.TicketViewModel

@Composable
fun PrioridadSpinner(
    idPrioridad: Int,
    prioridadViewModel: PrioridadViewModel =  hiltViewModel(),
    ticketViewModel: TicketViewModel = hiltViewModel()
) {

    var writeNamePrioridad: String

    writeNamePrioridad= getNombrePrioridad(idPrioridad)

    var mExpanded by remember { mutableStateOf(false) }

    val prioridades = prioridadViewModel.prioridades.collectAsState(initial = emptyList())

    var mSelectedText by remember {
        mutableStateOf(writeNamePrioridad)
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
            value = writeNamePrioridad,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .clickable { }
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = { Text(stringResource(R.string.Prioridad)) },
            trailingIcon = {
                Icon(
                    icon,
                    stringResource(R.string.Prioridad),
                    Modifier.clickable { mExpanded = !mExpanded }
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.PriorityHigh, contentDescription = null)
            },
            readOnly = true
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            prioridades.value.forEach {
                DropdownMenuItem(onClick = {
                    ticketViewModel.prioridadId = it.prioridadId
                    mSelectedText = it.descripcion
                    mExpanded = false
                }) {
                    Text(text = it.descripcion)
                }
            }
        }
    }
}

@Composable
fun getNombrePrioridad(prioridad: Int, viewModel: PrioridadViewModel = hiltViewModel()): String {
    var name: String = ""
    val prioridades = viewModel.prioridades.collectAsState(initial = emptyList())

    prioridades.value.forEach { element ->
        if (prioridad == element.prioridadId) {
            name = element.descripcion
        }
    }

    return name
}