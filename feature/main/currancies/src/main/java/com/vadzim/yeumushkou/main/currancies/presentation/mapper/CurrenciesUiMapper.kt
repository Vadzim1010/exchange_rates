package com.vadzim.yeumushkou.main.currancies.presentation.mapper

import com.vadzim.yeumushkou.core.presentation.state.CurrencyItemState
import com.vadzim.yeumushkou.domain.model.Rate

internal fun List<Rate>.map() = map { rate ->
    CurrencyItemState(
        baseCurrency = rate.currency.name,
        value = rate.value.toString(),
        isFavorite = rate.isFavorite
    )
}