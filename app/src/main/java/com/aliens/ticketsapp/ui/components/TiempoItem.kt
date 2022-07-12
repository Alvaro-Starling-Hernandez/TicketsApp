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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aliens.ticketsapp.model.Tiempo
import com.aliens.ticketsapp.ui.theme.TicketsAppTheme

@Composable
fun TiempoItem(
    //tiempo: Tiempo
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
            Box(
                modifier = Modifier
                    .size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text("nombre del tecnico")
                Text("Trabajo ..............")
                Text("60 minutos")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Previewe() {
    TicketsAppTheme {
        TiempoItem()
    }
}