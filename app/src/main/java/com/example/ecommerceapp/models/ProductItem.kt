package com.example.ecommerceapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerceapp.utils.Constants

@Entity(tableName = Constants.TABLE_PRODUCTS)
data class ProductItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String,
    val description: String,
    val image: String,
    val price: String,
    val title: String
)