package com.vadzim.yeumushkou.data.di

import android.content.Context
import androidx.room.Room
import com.vadzim.yeumushkou.data.bd.CurrencyDao
import com.vadzim.yeumushkou.data.bd.FavoriteCurrenciesDatabase
import dagger.Module
import dagger.Provides

@Module
internal class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): FavoriteCurrenciesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            FavoriteCurrenciesDatabase::class.java,
            "currency_database"
        ).build()
    }

    @Provides
    fun provideCurrencyDao(database: FavoriteCurrenciesDatabase): CurrencyDao {
        return database.currencyDao()
    }

}