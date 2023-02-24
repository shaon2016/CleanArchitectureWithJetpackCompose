package com.shaon2016.compose.data.remote.product

import retrofit2.http.GET

internal interface ProductApiService {
    @GET("api/products")
    suspend fun fetchProducts() : ProductListResponse
}