package com.github.androiddeveloper3005.roomdatabase_testing.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.androiddeveloper3005.roomdatabase_testing.data.local.ShoppingItem
import com.github.androiddeveloper3005.roomdatabase_testing.data.remote.responses.ImageResponse
import com.github.androiddeveloper3005.roomdatabase_testing.other.Resource

class FakeShoppingRepository : ShoppingRepository{
    private val shoppingItems = mutableListOf<ShoppingItem>()
    private val observeAllShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observeTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value : Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observeAllShoppingItems.postValue(shoppingItems)
        observeTotalPrice.postValue(getTotalPrice())

    }

    private fun getTotalPrice(): Float? {
        return shoppingItems.sumByDouble { it.price.toDouble() }.toFloat()

    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()

    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()

    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return observeAllShoppingItems
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observeTotalPrice
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if (shouldReturnNetworkError){
            Resource.error("Error",null)
        }else{
            Resource.success(ImageResponse(listOf(),0,0))
        }

    }
}