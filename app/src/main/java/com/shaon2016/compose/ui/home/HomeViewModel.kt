package com.shaon2016.compose.ui.home

import androidx.lifecycle.viewModelScope
import com.shaon2016.compose.domain.usecase.ProductListUseCase
import com.shaon2016.core.domain.Result
import com.shaon2016.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val productListUseCase: ProductListUseCase
) : BaseViewModel<HomeContract.Event, HomeContract.Effect, HomeContract.State>() {

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }

            when (val result = productListUseCase(Unit)) {
                is Result.Success -> {
                    setState {
                        copy(
                            isLoading = false,
                            products = result.data,
                            dataState = DataState.SUCCESS
                        )
                    }
                }
                is Result.Error -> {
                    setState { copy(isLoading = false, dataState = DataState.FAIL) }
                    result.exception.printStackTrace()
                }
            }
        }
    }

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.SelectProduct ->
                setEffect {
                    HomeContract.Effect.Navigation.ToProductDetail(event.product)
                }
        }
    }

    override fun setInitialState() = HomeContract.State()
}