package com.vadzim.yeumushkou.domain.usecase

import com.vadzim.yeumushkou.domain.model.Currency
import com.vadzim.yeumushkou.domain.model.FavoriteRate
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesLocalRepository
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesRemoteRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoritesRatesUseCase(
    private val remoteRepository: ExchangeRatesRemoteRepository,
    private val localRepository: ExchangeRatesLocalRepository,
) {

    suspend operator fun invoke(): Flow<Result<List<FavoriteRate>>> {
        return localRepository.getFavoriteExchangeRates().map { favoriteCurrencies ->
            coroutineScope {
                val favoriteRateDeferreds = favoriteCurrencies.map { favoriteCurrency ->
                    async {
                        runCatching {
                            val result = remoteRepository.getLatestExchangeRates(
                                baseCurrency = Currency.valueOf(favoriteCurrency.baseCurrency),
                                conversionCurrencies = listOf(Currency.valueOf(favoriteCurrency.relatedCurrency))
                            ).getOrThrow()

                            FavoriteRate(
                                currency = favoriteCurrency,
                                value = result.rates.first().value
                            )
                        }
                    }
                }

                runCatching {
                    favoriteRateDeferreds.map { it.await().getOrThrow() }
                }
            }
        }
    }
}