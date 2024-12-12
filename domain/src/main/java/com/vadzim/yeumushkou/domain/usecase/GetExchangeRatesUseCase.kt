package com.vadzim.yeumushkou.domain.usecase

import com.vadzim.yeumushkou.domain.model.Currency
import com.vadzim.yeumushkou.domain.model.ExchangeRates
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesLocalRepository
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

class GetExchangeRatesUseCase(
    private val remoteRepository: ExchangeRatesRemoteRepository,
    private val localRepository: ExchangeRatesLocalRepository,
) {

    suspend operator fun invoke(
        baseCurrency: Currency,
        conversionCurrencies: List<Currency>,
    ): Flow<Result<ExchangeRates>> {
        return flowOf(remoteRepository.getLatestExchangeRates(baseCurrency, conversionCurrencies))
            .combine(localRepository.getFavoriteExchangeRates()) { result, favorite ->
                result.map { exchangeRates ->
                    val rates = exchangeRates.rates.map { rate ->
                        val isFavorite = favorite.any { it.id == exchangeRates.baseCurrency.name + rate.currency.name }
                        rate.copy(isFavorite = isFavorite)
                    }
                    exchangeRates.copy(rates = rates)
                }
            }
    }

}