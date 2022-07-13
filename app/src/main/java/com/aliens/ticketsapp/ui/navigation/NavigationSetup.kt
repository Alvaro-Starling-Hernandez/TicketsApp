package com.aliens.ticketsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aliens.ticketsapp.ui.screens.respuesta.ConsultaRespuestaScreen
import com.aliens.ticketsapp.ui.screens.respuesta.RegistroRespuestaScreen
import com.aliens.ticketsapp.ui.screens.tecnico.ConsultaTecnicoScreen
import com.aliens.ticketsapp.ui.screens.tecnico.RegistroTecnicoScreen
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
            ConsultaTecnicoScreen(navController)
        }
        composable(Screen.RegistroRespuesta.route) {
            RegistroRespuestaScreen(navController,0)
        }
        composable(Screen.RegistroTiempo.route) {
            RegistroTiempoScreen(navController,0)
        }
        composable(Screen.RegistroTecnico.route){
            RegistroTecnicoScreen(navController)
        }
        composable(Screen.ConsultaTiempo.route) {
            ConsultaTiempoScreen(navController)
        }
        composable(Screen.RegistroRespuesta.withArgsFormat(Screen.RegistroRespuesta.id),
            arguments = listOf(navArgument(Screen.RegistroRespuesta.id){type = NavType.IntType})) { backStackEntry ->
            backStackEntry.arguments?.getInt(Screen.RegistroRespuesta.id)?.let {
                RegistroRespuestaScreen(navController, backStackEntry.arguments?.getInt(Screen.RegistroRespuesta.id)!!)
            }
        }
        composable(Screen.RegistroTiempo.withArgsFormat(Screen.RegistroTiempo.id),
            arguments = listOf(navArgument(Screen.RegistroTiempo.id){type = NavType.IntType})) { backStackEntry ->
            backStackEntry.arguments?.getInt(Screen.RegistroTiempo.id)?.let {
                RegistroTiempoScreen(navController, backStackEntry.arguments?.getInt(Screen.RegistroTiempo.id)!!)
            }
        }
    }
}

