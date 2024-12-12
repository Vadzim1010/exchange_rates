package com.vadzim.yeumushkou.data.repository

import com.vadzim.yeumushkou.data.bd.CurrencyDao
import com.vadzim.yeumushkou.data.bd.entity.CurrencyEntity
import com.vadzim.yeumushkou.data.mapper.map
import com.vadzim.yeumushkou.domain.model.FavoriteExchangeRate
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExchangeRatesLocalRepositoryImpl @Inject constructor(
    private val currencyDao: CurrencyDao
) : ExchangeRatesLocalRepository {

    override suspend fun insertExchangeRate(baseCurrency: String, relatedCurrency: String) {
        val entity = CurrencyEntity(
            id = baseCurrency + relatedCurrency,
            baseCurrency = baseCurrency,
            relatedCurrency = relatedCurrency
        )

        currencyDao.insertCurrency(entity)
    }

    override suspend fun deleteExchangeRate(baseCurrency: String, relatedCurrency: String) {
        currencyDao.deleteCurrency(baseCurrency + relatedCurrency)
    }

    override suspend fun getFavoriteExchangeRates(): Flow<List<FavoriteExchangeRate>> {
        return currencyDao.getAllCurrencies().map()
    }

}