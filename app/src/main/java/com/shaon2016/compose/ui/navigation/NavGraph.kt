package com.shaon2016.compose.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.shaon2016.compose.ui.home.HomeContract
import com.shaon2016.compose.ui.home.HomeScreen

internal fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    navigation(
        route = Route.Root.route,
        startDestination = Route.Home.route
    ) {
        composable(
            route = Route.Home.route
        ) {
            HomeScreen { navigationEffect ->
                when (navigationEffect) {
                    is HomeContract.Effect.Navigation.NavigateUp -> navController.navigateUp()
                    is HomeContract.Effect.Navigation.ToProductDetail -> navController.navigate(
                        Route.ProductDetails.createRoute(product = navigationEffect.product)
                    )
                }
            }
        }
        composable(
            route = Route.ProductDetails.route
        ) {
            // TODO Implement product details screen
        }
    }
}