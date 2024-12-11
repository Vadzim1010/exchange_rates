package com.vadzim.yeumushkou.data.repository

import com.vadzim.yeumushkou.data.api.ExchangeRatesApiService
import com.vadzim.yeumushkou.data.mapper.map
import com.vadzim.yeumushkou.domain.model.Currency
import com.vadzim.yeumushkou.domain.model.ExchangeRates
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesRemoteRepository
import javax.inject.Inject

internal class ExchangeRatesRemoteRepositoryImpl @Inject constructor(
    private val apiService: ExchangeRatesApiService
) : ExchangeRatesRemoteRepository {

    override suspend fun getLatestExchangeRates(
        baseCurrency: Currency,
        conversionCurrencies: List<Currency>,
    ): Result<ExchangeRates> = runCatching {
        apiService.getLatestRates(
            symbols = conversionCurrencies.joinToString(","),
            base = baseCurrency.name
        ).map()
    }
}