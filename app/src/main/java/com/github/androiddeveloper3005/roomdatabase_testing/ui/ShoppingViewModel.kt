package com.github.androiddeveloper3005.roomdatabase_testing.ui

import androidx.lifecycle.ViewModel
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.androiddeveloper3005.roomdatabase_testing.data.local.ShoppingItem
import com.github.androiddeveloper3005.roomdatabase_testing.data.remote.responses.ImageResponse
import com.github.androiddeveloper3005.roomdatabase_testing.other.Constants
import com.github.androiddeveloper3005.roomdatabase_testing.other.Event
import com.github.androiddeveloper3005.roomdatabase_testing.other.Resource
import com.github.androiddeveloper3005.roomdatabase_testing.repository.ShoppingRepository
import kotlinx.coroutines.launch
import java.lang.Exception

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

    private fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name : String , amountString: String , priceString: String){
        // ematy for testing perpose
        if (name.isEmpty() || amountString.isEmpty() || priceString.isEmpty()){
            _insertShoppingItemStatus.postValue(Event(Resource.error("The field must not be empty"
                ,null)))
            return

        }
        if (name.length > Constants.MAX_NAME_LENGTH){
            _insertShoppingItemStatus.postValue(Event(Resource.error("The name of the item" +
                    "must not exceed ${Constants.MAX_NAME_LENGTH} characters",null)))
            return

        }
        if (priceString.length > Constants.MAX_PRICE_LENGTH){
            _insertShoppingItemStatus.postValue(Event(Resource.error("The price of the item" +
                    "must not exceed ${Constants.MAX_PRICE_LENGTH} characters",null)))
            return

        }

        val amount = try {
            amountString.toInt()
        }catch (ex : Exception){
            _insertShoppingItemStatus.postValue(Event(Resource.error("Please enter a valid amount "
                ,null)))
            return

        }

        val shoppingItem = ShoppingItem(name , amount , priceString.toFloat() ,
            _currentImageUrl.value?:"")
        insertShoppingItemIntoDb(shoppingItem)
        //clear current image url because after insert item we are
        // going back to shopping fragment to see the list of item.after
        // comming back we need an empty image view
        setCurImageUrl("")
        _insertShoppingItemStatus.postValue(Event(Resource.success(shoppingItem)))


    }

    fun searchForImage(imageQuery : String){
        // ematy for testing perpose
        if (imageQuery.isEmpty()){
            return
        }
        _images.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.searchForImage(imageQuery)
            _images.value = Event(response)
        }


    }


}