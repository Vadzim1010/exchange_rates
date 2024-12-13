package com.vadzim.yeumushkou.data.repository

import com.vadzim.yeumushkou.data.bd.CurrencyDao
import com.vadzim.yeumushkou.data.bd.entity.CurrencyEntity
import com.vadzim.yeumushkou.data.mapper.map
import com.vadzim.yeumushkou.domain.model.FavoriteCurrency
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExchangeRatesLocalRepositoryImpl @Inject constructor(
    private val currencyDao: CurrencyDao
) : ExchangeRatesLocalRepository {

    override suspend fun insertExchangeRate(id: String, baseCurrency: String, relatedCurrency: String) {
        val entity = CurrencyEntity(
            id = id,
            baseCurrency = baseCurrency,
            relatedCurrency = relatedCurrency
        )

        currencyDao.insertCurrency(entity)
    }

    override suspend fun deleteExchangeRate(id: String) {
        currencyDao.deleteCurrency(id)
    }

    override suspend fun getFavoriteExchangeRates(): Flow<List<FavoriteCurrency>> {
        return currencyDao.getAllCurrencies().map()
    }

}