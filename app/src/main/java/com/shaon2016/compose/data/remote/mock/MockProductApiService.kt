package com.shaon2016.compose.data.remote.mock

import com.shaon2016.compose.data.remote.product.Product
import com.shaon2016.compose.data.remote.product.ProductApiService
import com.shaon2016.compose.data.remote.product.ProductListResponse

internal class MockProductApiService : ProductApiService {
    override suspend fun fetchProducts(): ProductListResponse {
        return listOf(
            Product(
                id = 1,
                title = "Bike",
                price = 100000.34,
                description = "",
                category = "",
                image = "",
                rating = Product.Rating(
                    rate = 3.2,
                    count = 1
                )
            ),
            Product(
                id = 2,
                title = "Car",
                price = 2000000.34,
                description = "",
                category = "",
                image = "",
                rating = Product.Rating(
                    rate = 3.2,
                    count = 1
                )
            )
        )
    }
}