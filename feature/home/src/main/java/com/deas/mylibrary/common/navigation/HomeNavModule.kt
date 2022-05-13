package com.deas.mylibrary.common.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import com.deas.mylibrary.presentation.ui.HomeScreen
import com.deas.navigation.Screen
import com.deas.navigation.base.NavComposableModule

val homeScreenNavModule = NavComposableModule{ navHost, navController ->
    navHost.apply {
        composable(
            Screen.Home.name
        ){
            HomeScreen(navController = navController,
                categoryViewModel = hiltViewModel()
            )
        }
    }
}