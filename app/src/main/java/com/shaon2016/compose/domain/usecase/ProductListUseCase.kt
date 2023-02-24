package com.shaon2016.compose.domain.usecase

import com.shaon2016.compose.data.repository.ProductRepository
import com.shaon2016.compose.domain.entity.Product
import com.shaon2016.core.di.AppDispatchers
import com.shaon2016.core.di.Dispatcher
import com.shaon2016.core.domain.Result
import com.shaon2016.core.domain.UseCase
import com.shaon2016.core.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class ProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : UseCase<Unit, Result<List<Product>>>(dispatcher = ioDispatcher) {
    override suspend fun execute(p: Unit) = productRepository.fetchProducts()
}