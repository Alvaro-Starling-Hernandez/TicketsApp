package com.aliens.ticketsapp.ui.screens.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.ui.navigation.BottomNavItem
import com.aliens.ticketsapp.ui.theme.cedarvillecursive
import com.aliens.ticketsapp.ui.theme.jotiOne
import com.aliens.ticketsapp.utils.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreenIconApp(navController: NavController){

    LaunchedEffect(key1 = true){
        delay(1000)
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
        
        Spacer(modifier = Modifier.height(130.dp))
        
        Image(
                painter = painterResource(id = R.drawable.publicaci_n_de_facebook_940x788_px),
                contentDescription = "IconApp",
                modifier =  Modifier.height(300.dp)
                                    .width(300.dp)
        )

        Spacer(modifier = Modifier.height(230.dp))

        Image(painter = painterResource(id = R.drawable.alien),
            contentDescription = "Alien",
            modifier = Modifier
                .height(32.dp)
                .width(32.dp)
        )

        Text(text = "From Aliens Solutions SRL",
            style = MaterialTheme.typography.caption,
            color = Color.Gray
        )

    }
}

//@Preview(showBackground = true)
//@Composable
//fun splashIconPreview(){
//    SplashIcon()
//}
