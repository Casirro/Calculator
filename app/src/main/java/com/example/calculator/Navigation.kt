package com.example.calculator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class Navigation {
    @Composable
    fun Navigate(navHostController: NavHostController, viewModel: CalculatorViewModel) {
        NavHost(navController = navHostController, startDestination = "home"){
            composable("home"){
                MainScreen(viewModel = viewModel, navController = navHostController)
            }

            composable("simon"){
                SimonScreen(navController = navHostController)
            }


        }
    }
}