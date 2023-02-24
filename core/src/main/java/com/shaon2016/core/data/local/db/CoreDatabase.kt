package com.shaon2016.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaon2016.core.data.local.db.dao.ProductDao
import com.shaon2016.core.data.local.db.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class CoreDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}