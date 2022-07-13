package com.aliens.ticketsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                horizontalAlignment = Alignment.End
            ) {
                Text(text = tecnico.nombreTecnico)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = tecnico.email)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = tecnico.telefonoTecnico)
            }


        }
    }
}