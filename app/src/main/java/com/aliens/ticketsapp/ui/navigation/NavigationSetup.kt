package com.aliens.ticketsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aliens.ticketsapp.ui.screens.respuesta.ConsultaRespuestaScreen
import com.aliens.ticketsapp.ui.screens.respuesta.RegistroRespuestaScreen
import com.aliens.ticketsapp.ui.screens.tecnico.TecnicoScreen
import com.aliens.ticketsapp.ui.screens.tiempo.ConsultaTiempoScreen
import com.aliens.ticketsapp.ui.screens.tiempo.RegistroTiempoScreen
import com.aliens.ticketsapp.utils.Screen

@Composable
fun NavigationSetup(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            ConsultaRespuestaScreen(navController)
        }
        composable(BottomNavItem.Soluciones.route) {
            ConsultaTiempoScreen(navController)
        }
        composable(BottomNavItem.Tecnico.route) {
            TecnicoScreen(navController)
        }
        composable(Screen.RegistroRespuesta.route) {
            RegistroRespuestaScreen(navController)
        }
        composable(Screen.RegistroTiempo.route) {
            RegistroTiempoScreen(navController)
        }
        composable(Screen.ConsultaTiempo.route) {
            ConsultaTiempoScreen(navController)
        }
        /*composable(Screen.About.withArgsFormat(Screen.About.id),
            arguments = listOf(navArgument(Screen.About.id){type = NavType.StringType})) { backStackEntry ->
            backStackEntry.arguments?.getString(Screen.About.id)?.let {
                AboutScreen(navController, backStackEntry.arguments?.getString(Screen.About.id)!!)
            }
        }*/
    }
}
