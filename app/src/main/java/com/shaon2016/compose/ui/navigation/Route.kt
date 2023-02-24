package com.shaon2016.compose.ui.navigation

import com.shaon2016.compose.domain.entity.Product
import com.shaon2016.compose.util.Constants.MAIN_APP
import com.shaon2016.compose.util.NavArgs.PRODUCT
import com.shaon2016.compose.util.NavArgs.PRODUCT_ID
import com.shaon2016.core.util.JsonConverter

internal sealed class Route(val route: String) {
    object Root : Route(MAIN_APP)
    object Home : Route("$MAIN_APP/home")

    object ProductDetails : Route(
        "$MAIN_APP/product-details?" +
            "$PRODUCT={$PRODUCT}"
    ) {
        fun createRoute(product: Product) = "$MAIN_APP/product-details?" +
            "$PRODUCT=${JsonConverter.toJson(product, Product::class.java)}"
    }

    // Just for reference
    object Default : Route(
        "$MAIN_APP/default?" +
            "$PRODUCT_ID={$PRODUCT_ID}" +
            "$PRODUCT={$PRODUCT}"
    ) {
        fun createRoute(productId: Int, product: Product) = "$MAIN_APP/default?" +
            "$PRODUCT_ID=$productId" +
            "$PRODUCT=${JsonConverter.toJson(product, Product::class.java)}"
    }
}