package com.example.ecommerceapp.db.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerceapp.models.CartItem
import com.example.ecommerceapp.models.ProductItem


@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductItem>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductItem)

    @Query("SELECT * FROM products WHERE category LIKE :category")
    suspend fun getProductsByCategory(category: String): List<ProductItem>

    @Query("SELECT * FROM products WHERE id IN (:productIds)")
    suspend fun getProductsByIds(productIds: List<Int>): List<ProductItem>
    // Другие методы для работы с продуктами, если нужно
}

@Dao
interface CartItemDao {
    @Query("SELECT * FROM cart_items")
    suspend fun getAllCartItems(): List<CartItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Query("DELETE FROM cart_items ")
    suspend fun deleteALLCartItems()

    @Query("SELECT COUNT(*) > 0 FROM cart_items WHERE productId = :productId")
    suspend fun isProductInCart(productId: Int): Boolean


}