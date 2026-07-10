package com.stefaneicher.matt.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stefaneicher.matt.presentation.screens.characters.CharactersScreen
import com.stefaneicher.matt.presentation.screens.events.EventScrollScreen
import com.stefaneicher.matt.presentation.screens.home.HomeScreen
import com.stefaneicher.matt.presentation.screens.login.LoginScreen
import com.stefaneicher.matt.presentation.screens.points.PointAccountScreen
import com.stefaneicher.matt.presentation.screens.tasks.TaskBoardScreen

@Composable
fun MattNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToTasks = { navController.navigate(Screen.TaskBoard.route) },
                onNavigateToPoints = { navController.navigate(Screen.PointAccount.route) },
                onNavigateToEvents = { navController.navigate(Screen.EventScroll.route) },
                onNavigateToCharacters = { navController.navigate(Screen.Characters.route) }
            )
        }
        composable(Screen.TaskBoard.route) {
            TaskBoardScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.PointAccount.route) {
            PointAccountScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.EventScroll.route) {
            EventScrollScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Characters.route) {
            CharactersScreen(onBack = { navController.popBackStack() })
        }
    }
}
