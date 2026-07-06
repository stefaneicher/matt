package com.stefaneicher.matt.presentation.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object TaskBoard : Screen("task_board")
    data object ActionBoard : Screen("action_board")
    data object PointAccount : Screen("point_account")
    data object EventScroll : Screen("event_scroll")
    data object Characters : Screen("characters")
    data object FamilySetup : Screen("family_setup")
    data object Profile : Screen("profile")
}
