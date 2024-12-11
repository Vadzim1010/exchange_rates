package com.vadzim.yeumushkou.domain.repository.api

import com.vadzim.yeumushkou.domain.model.Currency
import com.vadzim.yeumushkou.domain.model.ExchangeRates

interface ExchangeRatesRemoteRepository {

    suspend fun getLatestExchangeRates(
        baseCurrency: Currency,
        conversionCurrencies: List<Currency>,
    ): Result<ExchangeRates>
}