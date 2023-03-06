package com.shaon2016.compose.ui.home

import com.shaon2016.compose.domain.entity.Product
import com.shaon2016.compose.domain.usecase.ProductListUseCase
import com.shaon2016.core.domain.Result
import com.shaon2016.core.util.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
internal class HomeViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val productListUseCase: ProductListUseCase = mock()
    private lateinit var viewModel: HomeViewModel

    private fun initViewModel() {
        viewModel = HomeViewModel(productListUseCase)
    }

    @Test
    fun `verify product list fetch is successful`() = runTest {
        whenever(productListUseCase(Unit))
            .thenReturn(Result.Success(products))

        initViewModel()
        advanceUntilIdle()

        verify(productListUseCase).invoke(Unit)

        assert(!viewModel.state.value.isLoading)
        assert(viewModel.state.value.products.size == 2)
        assert(viewModel.state.value.dataState == DataState.SUCCESS)
    }

    @Test
    fun `verify product list fetch is not successful`() = runTest {
        whenever(productListUseCase(Unit))
            .thenReturn(Result.Error(MockitoException("")))

        initViewModel()
        /*** for details https://developer.android.com/kotlin/coroutines/test **/
        advanceUntilIdle()

        verify(productListUseCase).invoke(Unit)

        assert(!viewModel.state.value.isLoading)
        assert(viewModel.state.value.products.isEmpty())
        assert(viewModel.state.value.dataState == DataState.FAIL)
    }

    @Test
    fun `verify select product event`() = runTest {
        initViewModel()
        viewModel.setEvent(HomeContract.Event.SelectProduct(products[0]))

        assert(viewModel.effect.first() is HomeContract.Effect.Navigation.ToProductDetail)
    }

}

private val products = listOf(
    Product(
        id = "asd",
        name = "Bike",
        price = 20000.3
    ),

    Product(
        id = "asde",
        name = "Cycle",
        price = 1400.3
    )
)

