package com.shaon2016.compose.domain.repository

import com.shaon2016.compose.data.remote.product.Product
import com.shaon2016.compose.data.remote.product.ProductApiService
import com.shaon2016.compose.data.remote.product.ProductListResponse
import com.shaon2016.compose.data.repository.ProductRepository
import com.shaon2016.core.domain.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.mockito.exceptions.base.MockitoException
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
internal class ProductRepositoryImplTest {
    private val productApiService: ProductApiService = mock()

    private val productRepository: ProductRepository = ProductRepositoryImpl(
        productApiService = productApiService
    )

    @After
    fun teardown() {
        reset(productApiService)
    }

    @Test
    fun `verify product fetch is successful`() = runTest {
        whenever(productApiService.fetchProducts())
            .thenReturn(
                listOf(
                    Product(
                        id = 1,
                        title = "Bike",
                        price = 100000.34,
                        description = "",
                        category = "",
                        image = "",
                        rating = Product.Rating(
                            rate = 3.2,
                            count = 1
                        )
                    )
                )
            )

        val result = productRepository.fetchProducts()

        assert(result is Result.Success)
    }

    @Test
    fun `verify product fetch is not successful`() = runTest {
        whenever(productApiService.fetchProducts())
            .thenThrow(MockitoException(""))

        val result = productRepository.fetchProducts()

        assert(result is Result.Error)
    }
}