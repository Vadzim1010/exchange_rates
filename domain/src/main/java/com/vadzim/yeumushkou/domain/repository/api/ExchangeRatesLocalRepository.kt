package com.vadzim.yeumushkou.domain.repository.api

import com.vadzim.yeumushkou.domain.model.FavoriteCurrency
import kotlinx.coroutines.flow.Flow

interface ExchangeRatesLocalRepository {

    suspend fun insertExchangeRate(id: String, baseCurrency: String, relatedCurrency: String)

    suspend fun deleteExchangeRate(id: String)

    suspend fun getFavoriteExchangeRates(): Flow<List<FavoriteCurrency>>

}