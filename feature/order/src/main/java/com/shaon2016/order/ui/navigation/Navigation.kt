package com.shaon2016.order.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
internal fun OrderNavigation(
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