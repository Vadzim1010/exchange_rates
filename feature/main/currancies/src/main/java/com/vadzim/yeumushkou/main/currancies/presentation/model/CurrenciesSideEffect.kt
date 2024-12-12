package com.vadzim.yeumushkou.main.currancies.presentation.model

import com.vadzim.yeumushkou.core.presentation.mvi.SideEffect
import com.vadzim.yeumushkou.domain.model.Currency

internal sealed interface CurrenciesSideEffect : SideEffect {

    sealed interface Domain : CurrenciesSideEffect {

        data class LoadExchangeRates(
            val baseCurrency: Currency,
            val conversionCurrencies: List<Currency>,
        ) : Domain

        data class UpdateFavorites(
            val baseCurrency: String,
            val relatedCurrency: String,
            val isFavorite: Boolean,
        ) : Domain
    }

    sealed interface Ui : CurrenciesSideEffect {

        data object ShowErrorDialog : Ui
    }
}