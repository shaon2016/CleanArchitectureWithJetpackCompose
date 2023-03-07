package com.shaon2016.order.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.shaon2016.order.ui.list.OrderListScreen

internal fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    navigation(
        route = Route.Root.route,
        startDestination = Route.OrderList.route
    ) {
        composable(
            route = Route.OrderList.route
        ) {
            OrderListScreen()
        }
    }
}