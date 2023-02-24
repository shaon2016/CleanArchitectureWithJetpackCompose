package com.shaon2016.core.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.shaon2016.core.data.local.db.CoreDbConstants
import com.shaon2016.core.data.local.db.model.ProductEntity

@Dao
abstract class ProductDao : BaseDao<ProductEntity>() {

    @Query("SELECT * FROM " + CoreDbConstants.TABLE_PRODUCT + " LIMIT 1")
    abstract suspend fun getUser(): ProductEntity

}