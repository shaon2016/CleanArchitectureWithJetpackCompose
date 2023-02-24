package com.shaon2016.core.di

import com.shaon2016.core.util.DefaultDispatcherProvider
import com.shaon2016.core.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Singleton
    @Provides
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

}