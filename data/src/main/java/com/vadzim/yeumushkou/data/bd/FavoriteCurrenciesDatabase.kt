package com.vadzim.yeumushkou.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vadzim.yeumushkou.data.bd.entity.CurrencyEntity

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class FavoriteCurrenciesDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

}