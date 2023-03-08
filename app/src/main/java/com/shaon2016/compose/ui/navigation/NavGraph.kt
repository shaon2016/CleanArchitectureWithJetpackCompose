package com.shaon2016.compose.ui.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.shaon2016.compose.ui.home.HomeContract
import com.shaon2016.compose.ui.home.HomeScreen
import com.shaon2016.compose.ui.productdetails.ProductDetailsScreen
import com.shaon2016.compose.util.NavArgs.PRODUCT

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
                    is HomeContract.Effect.Navigation.ToProductDetail -> navController.navigate(
                        Route.ProductDetails.createRoute(product = navigationEffect.product)
                    )
                }
            }
        }
        composable(
            route = Route.ProductDetails.route,
            arguments = listOf(
                navArgument(PRODUCT) { type = NavType.StringType }
            )
        ) {
            ProductDetailsScreen()
        }
    }
}