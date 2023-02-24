package com.shaon2016.compose.data.remote.mock

import com.shaon2016.compose.data.remote.product.ProductApiService
import com.shaon2016.compose.data.remote.product.ProductListResponse

internal class MockProductApiService : ProductApiService {
    override suspend fun fetchProducts(): ProductListResponse {
        return ProductListResponse(
            products = listOf(
                ProductListResponse.Product(
                    id = "2434DSD",
                    name = "Bike",
                    price = 100000.34
                ),
                ProductListResponse.Product(
                    id = "2434DSDf",
                    name = "Car",
                    price = 2000000.34
                )
            )
        )
    }
}