package com.vadzim.yeumushkou.main.currancies.presentation.mapper

import com.vadzim.yeumushkou.domain.model.Rate
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiState

internal fun List<Rate>.map() = map { rate ->
    CurrenciesUiState.Rate(
        currency = rate.currency.name,
        value = rate.value,
        isFavorite = rate.isFavorite
    )
}