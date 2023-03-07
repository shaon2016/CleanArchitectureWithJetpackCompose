package com.shaon2016.order

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.shaon2016.order.ui.navigation.OrderNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrderNavigation(navHostController = rememberNavController())
        }
    }
}
