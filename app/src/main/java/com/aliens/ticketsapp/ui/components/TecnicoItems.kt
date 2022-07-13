package com.aliens.ticketsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aliens.ticketsapp.model.Tecnico

@Composable
fun TecnicoItems(
    tecnico: Tecnico,
    navController: NavController
){
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                Text(
                    text = tecnico.nombreTecnico,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(){
                    Text(text = tecnico.email)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "tel: " + tecnico.telefonoTecnico)
            }


        }
    }
}