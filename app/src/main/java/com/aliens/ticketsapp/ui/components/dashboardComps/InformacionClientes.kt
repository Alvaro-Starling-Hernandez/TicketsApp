package com.aliens.ticketsapp.ui.components.dashboardComps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.theme.jotiOne

@Composable
fun InformacionClientes(total: Int) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Informacion de Clientes",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = jotiOne,
            fontStyle = FontStyle.Italic
        )
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 10.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        ) {

            Image(
                painter = painterResource(id = R.drawable.fondo_verde),
                contentDescription = "Fondo verde",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(200.dp)
                    .height(80.dp)
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                    Text(
                        text = "Clientes Totales ",
                        style = MaterialTheme.typography.h6,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.padding(top = 0.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "$total",
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.End,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier
                            .padding(top = 0.dp)
                            .align(Alignment.End),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
            }
        }
    }
}