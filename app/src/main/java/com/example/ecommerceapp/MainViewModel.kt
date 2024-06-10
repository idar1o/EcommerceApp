package com.example.ecommerceapp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.models.CartItem
import com.example.ecommerceapp.models.ProductItem
import com.example.ecommerceapp.db.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

    private val dataRepository: DataRepository
) : ViewModel() {
    val listProducts = mutableStateOf<List<ProductItem>>(listOf())
    val listCartItem = mutableStateOf<List<ProductItem>>(listOf())

    val isProductInCartState = mutableStateOf(false)
    val listProductProgress = mutableStateOf(false)

    val errorMessage = mutableStateOf<String?>(null)

    fun getAllProducts() {
        viewModelScope.launch {
            try {
                listProductProgress.value = true
                dataRepository.fetchAndSaveAllProducts()
                listProducts.value =dataRepository.getAllProducts()
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Unknown error"
            } finally {
                listProductProgress.value = false
            }
        }
    }

     fun isProductInCart(productId: Int){
        viewModelScope.launch {
            try {
                isProductInCartState.value = dataRepository.isProductInCart(productId)

            } catch (e: Exception){
                e.message?.let { Log.d("Lol", it) }
            }

        }
    }
    fun insertCartItem ( item : ProductItem){
        viewModelScope.launch {
            try {
                listProductProgress.value = true
                dataRepository.insertCartItem(
                    CartItem(
                        productId = item.id,
                    )
                )
                isProductInCart(item.id)
                Log.d("Lol", "fun insertCartItem ${item.title}")

            } catch (e: Exception) {
                e.message?.let { Log.d("Lol", it) }

            } finally {
                listProductProgress.value = false
            }
        }
    }
    fun getAllCartItem(){
        viewModelScope.launch {
            try {
                listProductProgress.value = true
                val listCartItems = dataRepository.getAllCartItems()
                val listProductIds: List<Int> = listCartItems.map{ it.productId }
                listCartItem.value = dataRepository.getProductsByIds(listProductIds)

                Log.d("Lol", " fun getAllCartItem items are ${listCartItem.value.count()}")
            } catch (e: Exception) {
                e.message?.let { Log.d("Lol", it) }
            } finally {
                listProductProgress.value = false
            }
        }
    }

    fun deleteALLCartItems(){
        viewModelScope.launch (Dispatchers.IO){
            dataRepository.deleteALLCartItems()
            listCartItem.value = listOf()
        }
    }
    fun getProductsByCategory(category: String){
        viewModelScope.launch (Dispatchers.IO){
            listProducts.value = dataRepository.getProductsByCategory(category)
        }
    }


}