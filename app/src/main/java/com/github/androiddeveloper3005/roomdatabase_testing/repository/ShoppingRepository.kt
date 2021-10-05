package com.github.androiddeveloper3005.roomdatabase_testing.repository

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import com.github.androiddeveloper3005.roomdatabase_testing.data.local.ShoppingItem
import com.github.androiddeveloper3005.roomdatabase_testing.data.remote.responses.ImageResponse
import com.github.androiddeveloper3005.roomdatabase_testing.other.Resource
import retrofit2.Response
interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem : ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}