package com.shaon2016.compose.data.remote.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

typealias ProductListResponse = List<Product>

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "price")
    val price: Double,
    @Json(name = "description")
    val description: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "rating")
    val rating: Rating
) {
    @JsonClass(generateAdapter = true)
    data class Rating(
        @Json(name = "rate")
        val rate: Double,
        @Json(name = "count")
        val count: Int
    )
}
