package com.aliens.ticketsapp.ui.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aliens.ticketsapp.model.Tecnico
import kotlin.math.round

@Composable
fun TecnicoItems(
    tecnico: Tecnico,
    navController: NavController
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    ModalTecnico(showDialog, { showDialog = false }, navController, tecnico.tecnicoId, tecnico)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { showDialog = true },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .height(70.dp)
                    .width(70.dp),
                shape = RoundedCornerShape(64.dp),
                backgroundColor = Color.Blue
            ) {
                Text(
                    text = InicalNombre(tecnico.nombreTecnico),
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(6.dp)
                )
            }

            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                Text(
                    text = tecnico.nombreTecnico,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row() {

                    Text(text = tecnico.email)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "tel: " + tecnico.telefonoTecnico,
                    style = MaterialTheme.typography.caption

                )
            }


        }
    }
}

fun InicalNombre(inicial: String): String {
    var i = inicial[0]
    return i.toString()
}

