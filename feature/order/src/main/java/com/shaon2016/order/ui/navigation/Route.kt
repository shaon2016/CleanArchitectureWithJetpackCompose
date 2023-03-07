package com.shaon2016.order.ui.navigation

import com.shaon2016.order.util.Constants.ORDER
import com.shaon2016.order.util.NavArgs.ORDER_ID

internal sealed class Route(val route: String) {
    object Root : Route(ORDER)
    object OrderList : Route("$ORDER/order-list")
    object OrderDetails : Route(
        "$ORDER/order-details?" +
            "$ORDER_ID={$ORDER_ID}"
    ) {
        fun createRoute(orderId: String) = "$ORDER/order-details?" +
            "$ORDER_ID=$orderId"
    }
}