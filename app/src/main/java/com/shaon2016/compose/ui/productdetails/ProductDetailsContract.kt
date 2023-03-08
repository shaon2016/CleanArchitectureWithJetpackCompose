package com.shaon2016.compose.ui.productdetails

import com.shaon2016.compose.domain.entity.Product
import com.shaon2016.core.ui.ViewEvent
import com.shaon2016.core.ui.ViewSideEffect
import com.shaon2016.core.ui.ViewState

internal class ProductDetailsContract {
    sealed class Event : ViewEvent

    data class State(
        val isLoading: Boolean = false,
        val product: Product = Product(
            id = "",
            name = "",
            price = 0.0
        )
    ) : ViewState

    sealed class Effect : ViewSideEffect
}