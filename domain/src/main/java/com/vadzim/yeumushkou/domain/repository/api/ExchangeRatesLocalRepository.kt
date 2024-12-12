package com.vadzim.yeumushkou.domain.repository.api

import com.vadzim.yeumushkou.domain.model.FavoriteExchangeRate
import kotlinx.coroutines.flow.Flow

interface ExchangeRatesLocalRepository {

    suspend fun insertExchangeRate(baseCurrency: String, relatedCurrency: String)

    suspend fun deleteExchangeRate(baseCurrency: String, relatedCurrency: String)

    suspend fun getFavoriteExchangeRates(): Flow<List<FavoriteExchangeRate>>

}