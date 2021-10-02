package com.github.androiddeveloper3005.roomdatabase_testing.repository

import androidx.lifecycle.LiveData
import com.github.androiddeveloper3005.roomdatabase_testing.data.local.ShoppingDao
import com.github.androiddeveloper3005.roomdatabase_testing.data.local.ShoppingItem
import com.github.androiddeveloper3005.roomdatabase_testing.data.remote.PixabayAPI
import com.github.androiddeveloper3005.roomdatabase_testing.data.remote.responses.ImageResponse
import com.github.androiddeveloper3005.roomdatabase_testing.other.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository{
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred.",null)

            }else{
                Resource.error("An unknown error occurred", null)
            }

        }catch (e : Exception){
            Resource.error("We couldn't reach the server..Please check your internet connection",null)
        }
    }
}