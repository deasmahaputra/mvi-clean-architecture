package com.deas.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.deas.mvi.theme.ComposeBaseTheme
import dagger.hilt.android.AndroidEntryPoint
import com.deas.navigation.Screen
import com.deas.navigation.base.loadMultiNavComposable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            ComposeBaseTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.params
                ) {
                    loadMultiNavComposable(navController)
                }

            }
        }
    }
}