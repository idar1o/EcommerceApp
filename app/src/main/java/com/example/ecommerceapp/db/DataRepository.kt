package com.example.ecommerceapp.db

import com.example.ecommerceapp.models.CartItem
import com.example.ecommerceapp.models.ProductItem
import com.example.ecommerceapp.db.Dao.CartItemDao
import com.example.ecommerceapp.db.Dao.ProductDao
import com.example.ecommerceapp.network.ApiService
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao,
    private val cartItemDao: CartItemDao
) {
    suspend fun fetchAndSaveAllProducts() {
        try {
            val response = apiService.getAllProducts()
            if (response.isSuccessful) {
                response.body()?.let { products ->
                    productDao.insertProducts(products)
                }
            }
        } catch (e: Exception) {
            // Обработка ошибок
        }
    }
    suspend fun getAllProducts(): List<ProductItem> {
        return productDao.getAllProducts()
    }

    suspend fun insertProduct(product: ProductItem) {
        productDao.insertProduct(product)
    }
    suspend fun getProductsByCategory(category: String): List<ProductItem> {
        return productDao.getProductsByCategory(category)
    }

    suspend fun getAllCartItems(): List<CartItem> {
        return cartItemDao.getAllCartItems()
    }

    suspend fun insertCartItem(cartItem: CartItem) {
        cartItemDao.insertCartItem(cartItem)
    }

    suspend fun isProductInCart(cartItemId: Int): Boolean {
        return cartItemDao.isProductInCart(cartItemId)
    }

    suspend fun deleteALLCartItems() {
        cartItemDao.deleteALLCartItems()
    }

    suspend fun getProductsByIds(productIds: List<Int>): List<ProductItem>{
        return productDao.getProductsByIds(productIds)
    }
}
