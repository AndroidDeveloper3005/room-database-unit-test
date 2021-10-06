package com.github.androiddeveloper3005.roomdatabase_testing.repositories

import androidx.lifecycle.LiveData
import com.androiddevs.shoppinglisttestingyt.data.remote.responses.ImageResponse
import com.github.androiddeveloper3005.roomdatabase_testing.other.Resource
import com.github.androiddeveloper3005.roomdatabase_testing.data.local.ShoppingItem

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}