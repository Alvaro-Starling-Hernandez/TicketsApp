package com.aliens.ticketsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aliens.ticketsapp.ui.screens.cliente.ConsultaClienteScreen
import com.aliens.ticketsapp.ui.screens.cliente.RegistroClienteScreen
import com.aliens.ticketsapp.ui.screens.respuesta.ConsultaRespuestaScreen
import com.aliens.ticketsapp.ui.screens.respuesta.RegistroRespuestaScreen
import com.aliens.ticketsapp.ui.screens.tecnico.ConsultaTecnicoScreen
import com.aliens.ticketsapp.ui.screens.tecnico.RegistroTecnicoScreen
import com.aliens.ticketsapp.ui.screens.ticket.ConsultaTicketScreen
import com.aliens.ticketsapp.ui.screens.ticket.RegistroTicketScreen
//import com.aliens.ticketsapp.ui.screens.ticket.RegistroTicketScreen
import com.aliens.ticketsapp.ui.screens.tiempo.ConsultaTiempoScreen
import com.aliens.ticketsapp.ui.screens.tiempo.RegistroTiempoScreen
import com.aliens.ticketsapp.utils.Screen

@Composable
fun NavigationSetup(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(Screen.ConsultaTiempo.route) {
            ConsultaTiempoScreen(navController)
        }
        composable(BottomNavItem.Tecnico.route) {
            ConsultaTecnicoScreen(navController)
        }
        composable(BottomNavItem.Home.route) {
            ConsultaTicketScreen(navController)
        }
        composable(BottomNavItem.Cliente.route) {
            ConsultaClienteScreen(navController)
        }
        composable(Screen.ConsultaRespuesta.route) {
            ConsultaRespuestaScreen(navController)
        }
        composable(Screen.RegistroRespuesta.route) {
            RegistroRespuestaScreen(navController,0)
        }
        composable(Screen.RegistroTiempo.route) {
            RegistroTiempoScreen(navController,0)
        }
        composable(Screen.RegistroCliente.route) {
            RegistroClienteScreen(navController,0)
        }
        composable(Screen.RegistroTecnico.route){
            RegistroTecnicoScreen(navController, 0)
        }
        composable(Screen.RegistroTicket.route){
            RegistroTicketScreen(navController, 0)
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
        composable(Screen.RegistroTecnico.withArgsFormat(Screen.RegistroTecnico.id),
            arguments = listOf(navArgument(Screen.RegistroTecnico.id){type = NavType.IntType})) { backStackEntry ->
            backStackEntry.arguments?.getInt(Screen.RegistroTecnico.id)?.let {
                RegistroTecnicoScreen(navController, backStackEntry.arguments?.getInt(Screen.RegistroTecnico.id)!!)
            }
        }
        composable(Screen.RegistroCliente.withArgsFormat(Screen.RegistroCliente.id),
            arguments = listOf(navArgument(Screen.RegistroCliente.id){type = NavType.IntType})) { backStackEntry ->
            backStackEntry.arguments?.getInt(Screen.RegistroCliente.id)?.let {
                RegistroClienteScreen(navController, backStackEntry.arguments?.getInt(Screen.RegistroCliente.id)!!)
            }
        }
        composable(Screen.RegistroTicket.withArgsFormat(Screen.RegistroTicket.id),
            arguments = listOf(navArgument(Screen.RegistroTicket.id){type = NavType.IntType})) { backStackEntry ->
            backStackEntry.arguments?.getInt(Screen.RegistroTicket.id)?.let {
                RegistroTicketScreen(navController, backStackEntry.arguments?.getInt(Screen.RegistroTicket.id)!!)
            }
        }
    }
}


