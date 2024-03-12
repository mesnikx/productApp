package com.example.productapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val title: String,
    val description: String,
    @SerialName(value = "thumbnail")
    val thumbnail: String
)
