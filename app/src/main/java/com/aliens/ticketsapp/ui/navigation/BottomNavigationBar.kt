package com.aliens.ticketsapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aliens.ticketsapp.R
import com.aliens.ticketsapp.utils.Screen

sealed class BottomNavItem(
    val route: String,
    @StringRes val titleResId: Int,
    val icon: ImageVector
) {
    object DashBoard : BottomNavItem(
        route = Screen.DashBoard.route,
        titleResId = R.string.DashBoard,
        icon = Icons.Default.Home
    )

    object Home : BottomNavItem(
        route = Screen.ConsultaTicket.route,
        titleResId = R.string.Tickets,
        icon = Icons.Default.BookOnline
    )

    object Tecnico : BottomNavItem(
        route = Screen.ConsultaTecnico.route,
        titleResId = R.string.Tecnico,
        icon = Icons.Default.Engineering
    )

    object Cliente : BottomNavItem(
        route = Screen.ConsultaCliente.route,
        titleResId = R.string.Cliente,
        icon = Icons.Default.Person
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.DashBoard,
        BottomNavItem.Home,
        BottomNavItem.Tecnico,
        BottomNavItem.Cliente
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(id = item.titleResId)
                    )
                },
                label = { Text(text = stringResource(id = item.titleResId)) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {

                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
