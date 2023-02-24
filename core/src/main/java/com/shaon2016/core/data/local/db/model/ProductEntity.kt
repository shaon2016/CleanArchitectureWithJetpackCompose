package com.shaon2016.core.data.local.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shaon2016.core.data.local.db.CoreDbConstants

@Entity(tableName = CoreDbConstants.TABLE_PRODUCT)
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String
)