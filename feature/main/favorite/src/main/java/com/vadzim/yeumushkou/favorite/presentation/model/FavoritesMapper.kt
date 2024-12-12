package com.vadzim.yeumushkou.favorite.presentation.model

import com.vadzim.yeumushkou.core.presentation.state.CurrencyItemState
import com.vadzim.yeumushkou.domain.model.FavoriteRate

internal fun List<FavoriteRate>.map(): List<CurrencyItemState> {
    return map { it.map() }
}

internal fun FavoriteRate.map(): CurrencyItemState {
    return CurrencyItemState(
        baseCurrency = currency.baseCurrency,
        relatedCurrency = currency.relatedCurrency,
        value = value.toString(),
        isFavorite = true,
    )
}