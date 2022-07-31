package com.aliens.ticketsapp.ui.screens.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.aliens.ticketsapp.R

@Composable
fun SplashScreenIconApp(navController: NavController){
    SplashIcon()
}

@Composable
fun SplashIcon(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.fondo21),
            contentDescription = "TicketsApp"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun splashIconPreview(){
    SplashIcon()
}
