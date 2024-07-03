package com.hasnast.job1restapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasnast.job1restapi.ApiClient.apiService
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        viewModelScope.launch {
            fetchProducts()
        }
    }

    private suspend fun fetchProducts() {
        try {
            val response = apiService.getProducts()
            _products.postValue(response)
        } catch (e: Exception) {
            // Handle error
        }
    }
}