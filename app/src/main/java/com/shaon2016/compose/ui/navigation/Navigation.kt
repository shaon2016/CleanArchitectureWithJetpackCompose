package com.shaon2016.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
internal fun MainNavigation(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = "main",
        startDestination = Route.Root.route
    ) {
        mainNavGraph(navHostController)
    }
}