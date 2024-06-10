package com.example.ecommerceapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerceapp.utils.Constants

@Entity(tableName = Constants.TABLE_CART)
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Int
)