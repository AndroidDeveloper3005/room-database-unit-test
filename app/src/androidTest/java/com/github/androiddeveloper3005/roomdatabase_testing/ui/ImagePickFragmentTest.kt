package com.github.androiddeveloper3005.roomdatabase_testing.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.github.androiddeveloper3005.roomdatabase_testing.R
import com.github.androiddeveloper3005.roomdatabase_testing.adapters.ImageAdapter
import com.github.androiddeveloper3005.roomdatabase_testing.launchFragmentInHiltContainer
import com.github.androiddeveloper3005.roomdatabase_testing.repositories.FakeShoppingRepositoryAndroidTest
import com.github.androiddeveloper3005.shopinglist.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImagePickFragmentTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get : Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var fragmentFactory: ShoppingFragmentFactory

    @Before
    fun setUp(){
        hiltRule.inject()
    }


    @Test
    fun `clickImage_popbackstackAndSetImageUrl`(){
        val navController = mock(NavController::class.java)
        val imageUrl = "TEST"
        val testViewModel = ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
        launchFragmentInHiltContainer<ImagePickFragment>(fragmentFactory = fragmentFactory) {
            Navigation.setViewNavController(requireView(),navController)
            imageAdapter.images =  listOf(imageUrl)

        }

        onView(withId(R.id.rvImages)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageViewHolder>(
                0,
                click()
            )
        )

        verify(navController).popBackStack()
        assertThat(testViewModel.curImageUrl.getOrAwaitValue()).isEqualTo(imageUrl)

    }


}