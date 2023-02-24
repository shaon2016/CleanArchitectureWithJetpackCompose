package com.shaon2016.compose.di

import com.shaon2016.compose.data.remote.mock.MockProductApiService
import com.shaon2016.compose.data.remote.product.ProductApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object ApiServiceModule {

    @ActivityRetainedScoped
    @Provides
    fun providerProductListApiService(retrofit: Retrofit): ProductApiService {
//        return retrofit.create(ProductApiService::class.java)
        return MockProductApiService()
    }
}