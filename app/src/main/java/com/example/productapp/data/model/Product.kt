package com.example.productapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class  ProductsList(
    @SerialName("products")
    val products: List<Product>
)

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    @SerialName(value = "thumbnail")
    val thumbnail: String
)
