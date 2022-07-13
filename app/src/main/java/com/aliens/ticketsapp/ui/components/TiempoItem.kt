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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliens.ticketsapp.model.Respuesta
import com.aliens.ticketsapp.model.Tiempo
import com.aliens.ticketsapp.ui.theme.TicketsAppTheme
import com.aliens.ticketsapp.utils.Screen

@Composable
fun TiempoItem(
    tiempo: Tiempo,
    navController: NavController
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    ModalTiempo(showDialog, { showDialog = false }, navController, tiempo.tiempoId, tiempo)

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .clickable{ showDialog = true }
    ) {
        Row(modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
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
                Text(text = getNombreTecnico(tecn = tiempo.tecnicoId), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = tiempo.trabajo, maxLines = 1, overflow = TextOverflow.Ellipsis, fontStyle = FontStyle.Italic)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = tiempo.tiempo.toString()+" mins")
            }
        }

    }
}