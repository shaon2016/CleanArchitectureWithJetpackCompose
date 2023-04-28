package com.shaon2016.compose.ui.productdetails

import androidx.lifecycle.SavedStateHandle
import com.shaon2016.compose.domain.entity.Product
import com.shaon2016.compose.util.NavArgs
import com.shaon2016.core.ui.BaseViewModel
import com.shaon2016.core.util.JsonConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ProductDetailsContract.Event, ProductDetailsContract.Effect,
    ProductDetailsContract.State>() {

    init {
        try {
            val product = JsonConverter.fromJson(
                savedStateHandle.get<String>(NavArgs.PRODUCT)!!,
                Product::class.java
            )!!

            setState { copy(product = product) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun handleEvents(event: ProductDetailsContract.Event) {

    }

    override fun setInitialState() = ProductDetailsContract.State()
}