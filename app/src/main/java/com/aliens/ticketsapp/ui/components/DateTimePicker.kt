package com.aliens.ticketsapp.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Pinch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.screens.respuesta.RespuestaViewModel
import java.util.*

@Composable
fun DateTimePicker(viewModel: RespuestaViewModel = hiltViewModel()) {
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.fecha = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )

    Column(
    ) {
        OutlinedTextField(
            value = viewModel.fecha,
            onValueChange = { viewModel.fecha = it },
            modifier = Modifier
                .clickable { mDatePickerDialog.show() }
                .fillMaxWidth(),
            label = { Text(stringResource(R.string.SelectDate)) },
            trailingIcon = {
                Icon(
                    Icons.Default.Pinch,
                    stringResource(R.string.SelectDate),
                    Modifier.clickable { mDatePickerDialog.show() }
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null)
            },
            readOnly = true
        )
    }
}