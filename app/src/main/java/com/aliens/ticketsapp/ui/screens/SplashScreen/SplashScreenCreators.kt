package com.aliens.ticketsapp.ui.screens.SplashScreen

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.aliens.ticketsapp.R

@Composable
fun SplashScreenCreators(){
    Splash()
}

@Composable
fun Splash(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.fondo22),
            contentDescription = "Aliens Solutions SRL",

        )
    }
}

@Preview(showBackground = true)
@Composable
fun splashPreview(){
    Splash()
}
