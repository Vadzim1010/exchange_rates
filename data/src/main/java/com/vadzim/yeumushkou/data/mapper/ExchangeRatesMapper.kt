package com.vadzim.yeumushkou.data.mapper

import com.vadzim.yeumushkou.data.bd.entity.CurrencyEntity
import com.vadzim.yeumushkou.data.dto.ExchangeRatesResponse
import com.vadzim.yeumushkou.domain.model.Currency
import com.vadzim.yeumushkou.domain.model.ExchangeRates
import com.vadzim.yeumushkou.domain.model.FavoriteExchangeRate
import com.vadzim.yeumushkou.domain.model.Rate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal fun ExchangeRatesResponse.map(): ExchangeRates {
    return ExchangeRates(
        baseCurrency = Currency.valueOf(base),
        rates = rates.map { (currency, rate) ->
            Rate(
                currency = currency,
                value = rate,
                isFavorite = false
            )
        }
    )
}

internal fun Flow<List<CurrencyEntity>>.map(): Flow<List<FavoriteExchangeRate>> = map { it.map() }

internal fun List<CurrencyEntity>.map(): List<FavoriteExchangeRate> = map { it.map() }

internal fun CurrencyEntity.map(): FavoriteExchangeRate {
    return FavoriteExchangeRate(
        id = id,
        baseCurrency = baseCurrency,
        relatedCurrency = relatedCurrency,
    )
}