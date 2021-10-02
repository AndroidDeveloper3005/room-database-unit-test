package com.github.androiddeveloper3005.roomdatabase_testing.dependenciesinjection

import android.content.Context
import androidx.room.Room
import com.github.androiddeveloper3005.roomdatabase_testing.data.local.ShoppingItemDatabase
import com.github.androiddeveloper3005.roomdatabase_testing.data.remote.PixabayAPI
import com.github.androiddeveloper3005.roomdatabase_testing.other.Constants.BASE_URL
import com.github.androiddeveloper3005.roomdatabase_testing.other.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    // for room database
    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,ShoppingItemDatabase::class.java , DATABASE_NAME).build()

    // for room database
    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()


    // for retrofit
    @Singleton
    @Provides
    fun providePixalBayAPI() : PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }
}