package com.aliens.ticketsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aliens.ticketsapp.ui.navigation.BottomNavigationBar
import com.aliens.ticketsapp.ui.navigation.NavigationSetup
import com.aliens.ticketsapp.ui.theme.TicketsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicketsAppTheme {
                // A surface container using the 'background' color from the them

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    var showBottomBar by rememberSaveable { mutableStateOf(true) }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    showBottomBar = when (navBackStackEntry?.destination?.route) {
                        "SplashScreenIconApp" -> false // on this screen bottom bar should be hidden
                        else -> true // in all other cases show bottom bar
                    }
                    Scaffold(
                        bottomBar = { if (showBottomBar) BottomNavigationBar(navController) }
                    ) {
                        NavigationSetup(navController = navController)
                    }
                }
            }
        }
    }
}

