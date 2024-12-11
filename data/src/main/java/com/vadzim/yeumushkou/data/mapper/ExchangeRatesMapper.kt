package com.vadzim.yeumushkou.data.mapper

import com.vadzim.yeumushkou.data.dto.ExchangeRatesResponse
import com.vadzim.yeumushkou.domain.model.Currency
import com.vadzim.yeumushkou.domain.model.ExchangeRates

internal fun ExchangeRatesResponse.map(): ExchangeRates {
    return ExchangeRates(
        baseCurrency = Currency.valueOf(base),
        rates = rates
    )
}