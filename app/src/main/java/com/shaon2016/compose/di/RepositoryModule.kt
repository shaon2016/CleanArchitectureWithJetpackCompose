package com.shaon2016.compose.di

import com.shaon2016.compose.data.repository.ProductRepository
import com.shaon2016.compose.domain.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface RepositoryModule {
    @Binds
    @ActivityRetainedScoped
    fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl) : ProductRepository
}