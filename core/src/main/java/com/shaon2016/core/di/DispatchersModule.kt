package com.shaon2016.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(AppDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(AppDispatchers.MAIN)
    fun providesMAINDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val appDispatcher: AppDispatchers)

enum class AppDispatchers {
    IO,
    MAIN
}