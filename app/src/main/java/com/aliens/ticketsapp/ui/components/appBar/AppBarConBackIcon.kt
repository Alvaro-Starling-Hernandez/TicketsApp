package com.aliens.ticketsapp.ui.components.appBar


import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AppBarConBackIcon(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    navigateIcon: @Composable () -> Unit,
    navController: NavController,
    title: String
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultAppBarConBackIcon(
                onSearchClicked = onSearchTriggered,
                navController = navController,
                title = title,
                navigateIcon = navigateIcon
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}