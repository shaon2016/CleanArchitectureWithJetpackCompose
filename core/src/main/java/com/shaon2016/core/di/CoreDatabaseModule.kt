package com.shaon2016.core.di

import android.content.Context
import androidx.room.Room
import com.shaon2016.core.data.local.db.CoreDatabase
import com.shaon2016.core.data.local.db.CoreDbConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseSource(@ApplicationContext context: Context): CoreDatabase {
        return Room.databaseBuilder(
            context,
            CoreDatabase::class.java,
            CoreDbConstants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}