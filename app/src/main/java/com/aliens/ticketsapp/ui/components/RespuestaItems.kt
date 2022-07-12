package com.aliens.ticketsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliens.ticketsapp.model.Respuesta

@Composable
fun RespuestaItem(
    respuesta: Respuesta
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .clickable { /*navegar*/ }
            .padding(8.dp)
        ) {

            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text("Nombre del Tecnico")
                Text(respuesta.Mensaje)
            }
            Text(
                respuesta.fecha,
                textAlign = TextAlign.End,
                letterSpacing = 3.sp,
                modifier = Modifier.padding(start = 60.dp)
            )
        }

    }
}