package com.shaon2016.core.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.*

object JsonConverter {
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun <T> fromJson(json: String, clazz: Class<T>): T? {
        return moshi.adapter(clazz).fromJson(json)
    }

    fun <T> toJson(value: T, clazz: Class<T>): String {
        return moshi.adapter(clazz).toJson(value)
    }
}