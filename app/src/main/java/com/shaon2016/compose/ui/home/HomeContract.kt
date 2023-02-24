package com.shaon2016.compose.ui.home

import com.shaon2016.compose.domain.entity.Product
import com.shaon2016.core.ui.ViewEvent
import com.shaon2016.core.ui.ViewSideEffect
import com.shaon2016.core.ui.ViewState

internal class HomeContract {
    sealed class Event : ViewEvent {
        object NavigateUp : Event()
        data class SelectProduct(val product: Product) : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val dataState: DataState = DataState.INITIAL,
        val products: List<Product> = emptyList()
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object NavigateUp : Navigation()
            data class ToProductDetail(val product: Product) : Navigation()
        }
    }
}

internal enum class DataState {
    INITIAL,
    SUCCESS,
    FAIL
}