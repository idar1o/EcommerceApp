package com.example.ecommerceapp.network

import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllProducts() = apiService.getAllProducts()

}