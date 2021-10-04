package com.github.androiddeveloper3005.roomdatabase_testing.ui

import androidx.lifecycle.ViewModel
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.androiddeveloper3005.roomdatabase_testing.data.local.ShoppingItem
import com.github.androiddeveloper3005.roomdatabase_testing.data.remote.responses.ImageResponse
import com.github.androiddeveloper3005.roomdatabase_testing.other.Event
import com.github.androiddeveloper3005.roomdatabase_testing.other.Resource
import com.github.androiddeveloper3005.roomdatabase_testing.repository.ShoppingRepository
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val repository: ShoppingRepository

) : ViewModel() {
    val shoppingItems = repository.observeAllShoppingItems()
    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images : LiveData<Event<Resource<ImageResponse>>> = _images

    //selected image from recycler view
    private val _currentImageUrl = MutableLiveData<String>()
    val currentImageUrl : LiveData<String> = _currentImageUrl


    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus : LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingItemStatus

    fun setCurImageUrl(url : String){
        _currentImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb( shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name : String , amountString: String , priceString: String){
        // ematy for testing perpose
    }

    fun searchForImage(imageQuery : String){
        // ematy for testing perpose

    }


}