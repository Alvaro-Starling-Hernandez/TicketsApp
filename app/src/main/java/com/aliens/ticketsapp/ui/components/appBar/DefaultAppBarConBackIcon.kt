package com.aliens.ticketsapp.ui.components.appBar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun DefaultAppBarConBackIcon(
    navController: NavController,
    title: String,
    onSearchClicked: () -> Unit,
    navigateIcon: @Composable () -> Unit
) {
    TopAppBar(
        title = {
            Text(title)
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            }
        },
        navigationIcon = {
            navigateIcon()
        }
    )
}