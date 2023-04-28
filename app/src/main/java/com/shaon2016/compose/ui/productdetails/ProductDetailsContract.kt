package com.shaon2016.compose.ui.productdetails

import com.shaon2016.compose.domain.entity.Product
import com.shaon2016.core.ui.ViewEvent
import com.shaon2016.core.ui.ViewSideEffect
import com.shaon2016.core.ui.ViewState

internal class ProductDetailsContract {
    sealed class Event : ViewEvent {
        object NavigateUp : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val product: Product = Product(
            id = "",
            title = "",
            price = 0.0
        )
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object NavigateUp : Navigation()
        }
    }
}