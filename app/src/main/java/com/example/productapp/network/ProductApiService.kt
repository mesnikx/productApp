package com.example.productapp.network

import com.example.productapp.data.model.Product
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

    private const val BASE_URL =
        "https://dummyjson.com/products"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    interface ProductApiService {
        @GET("thumbnails")
        suspend fun getThumbnails(): List<Product>
    }

    object ProductApi {
        val retrofitService: ProductApiService by lazy {
            retrofit.create(ProductApiService::class.java)
        }
    }