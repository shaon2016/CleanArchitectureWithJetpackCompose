package com.shaon2016.compose.data.remote.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ProductListResponse(
    @Json(name = "products")
    val products: List<Product>
) {
    @JsonClass(generateAdapter = true)
    data class Product(
        @Json(name = "id")
        val id: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "price")
        val price: Double
    )
}
