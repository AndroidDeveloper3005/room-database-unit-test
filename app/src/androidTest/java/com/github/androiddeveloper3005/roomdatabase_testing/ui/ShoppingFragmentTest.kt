package com.github.androiddeveloper3005.roomdatabase_testing.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.github.androiddeveloper3005.roomdatabase_testing.R
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
class ShoppingFragmentTest{
    @get : Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setUp(){
        hiltRule.inject()
    }

    //this is for testing fab button click
    @Test
    fun `clickAddShoppingButton_NAvigate_to_addShoppingItemFragment`(){
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<ShoppingFragment> {
           Navigation.setViewNavController(requireView(),navController)
        }
        onView(withId(R.id.fabAddShoppingItem)).perform(click())

        verify(navController).navigate(
            ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
        )
    }



}