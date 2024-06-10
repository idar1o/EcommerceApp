package com.example.ecommerceapp.network

import com.example.ecommerceapp.models.ProductItem
import com.example.ecommerceapp.utils.Constants
import retrofit2.http.GET
import retrofit2.Response


interface ApiService {
    @GET(Constants.GET_ALL)
    suspend fun getAllProducts() : Response<ArrayList<ProductItem>>

}