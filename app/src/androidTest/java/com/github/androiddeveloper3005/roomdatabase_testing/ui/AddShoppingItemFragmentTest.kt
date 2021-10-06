package com.github.androiddeveloper3005.roomdatabase_testing.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.pressBack
import androidx.test.filters.MediumTest
import com.github.androiddeveloper3005.roomdatabase_testing.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class AddShoppingItemFragmentTest{
    @get : Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun `pressBackButton_popBackStack`(){
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()

        verify(navController).popBackStack()
    }
}