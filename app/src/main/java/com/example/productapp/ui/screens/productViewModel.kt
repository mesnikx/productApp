package com.example.productapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productapp.data.model.Product
import com.example.productapp.data.model.ProductsList
import com.example.productapp.network.ProductApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface ProductUiState {
    data class Success(val productsList: ProductsList) : ProductUiState
    object Error : ProductUiState
    object Loading : ProductUiState
}

class ProductViewModel : ViewModel() {

    var productUiState: ProductUiState by mutableStateOf(ProductUiState.Loading)
        private set

    init {
        getProduct()
    }


    fun getProduct() {
        viewModelScope.launch {
            productUiState = ProductUiState.Loading
            productUiState = try {
                val listResult = ProductApi.retrofitService.getProducts()
                ProductUiState.Success(listResult)
            } catch (e: IOException) {
                ProductUiState.Error
            } catch (e: HttpException) {
                ProductUiState.Error
            }
        }
    }
}