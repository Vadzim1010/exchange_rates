package com.vadzim.yeumushkou.data.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vadzim.yeumushkou.data.bd.entity.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: CurrencyEntity)

    @Query("SELECT * FROM currency_database")
    fun getAllCurrencies(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currency_database WHERE baseCurrency = :baseCurrency")
    fun getCurrency(baseCurrency: String): Flow<CurrencyEntity>

    @Query("DELETE FROM currency_database WHERE id = :id")
    suspend fun deleteCurrency(id: String)

}