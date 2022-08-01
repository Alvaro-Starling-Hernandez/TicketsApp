package com.aliens.ticketsapp.ui.screens.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.navigation.BottomNavItem
import com.aliens.ticketsapp.utils.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreenIconApp(navController: NavController){

    LaunchedEffect(key1 = true){
        delay(5000)
        navController.popBackStack()
        navController.navigate(Screen.DashBoard.route)
    }

    SplashIcon()
}

@Composable
fun SplashIcon(){
    Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
       /* Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .fillMaxSize()
                .background(color = Color.Cyan),
            painter = painterResource(id = R.drawable.fondo21),
            contentDescription = "TicketsApp"
        )*/

        Image(painter = painterResource(id = R.drawable.dispensador),
                contentDescription = "IconApp")

        Text(text = "TicketsApp",
            fontWeight = FontWeight.Bold,
            //fontFamily = FontFamily.
            )

    }
}

//@Preview(showBackground = true)
//@Composable
//fun splashIconPreview(){
//    SplashIcon()
//}
