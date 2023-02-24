package com.shaon2016.compose.domain.repository

import com.shaon2016.compose.data.remote.product.ProductApiService
import com.shaon2016.compose.data.repository.ProductRepository
import com.shaon2016.compose.domain.entity.Product
import com.shaon2016.core.domain.Result
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    private val productApiService: ProductApiService
) : ProductRepository {
    override suspend fun fetchProducts(): Result<List<Product>> {
        return try {
            val response = productApiService.fetchProducts()

            val products = response.products.map {
                Product(
                    id = it.id,
                    name = it.name,
                    price = it.price
                )
            }

            Result.Success(products)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}