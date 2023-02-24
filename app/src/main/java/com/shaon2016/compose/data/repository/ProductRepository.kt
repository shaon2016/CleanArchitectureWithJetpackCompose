package com.shaon2016.compose.data.repository

import com.shaon2016.compose.domain.entity.Product
import com.shaon2016.core.domain.Result

internal interface ProductRepository {
    suspend fun fetchProducts(): Result<List<Product>>
}