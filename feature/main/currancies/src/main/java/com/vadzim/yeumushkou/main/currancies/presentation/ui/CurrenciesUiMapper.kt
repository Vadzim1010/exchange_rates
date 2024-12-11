package com.vadzim.yeumushkou.main.currancies.presentation.ui

import com.vadzim.yeumushkou.domain.model.Currency
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiState

internal fun Map<Currency, Double>.map() = map { (currency, rate) ->
    CurrenciesUiState.Rate(
        currency = currency.name,
        rate = rate,
        isFavorite = false
    )
}