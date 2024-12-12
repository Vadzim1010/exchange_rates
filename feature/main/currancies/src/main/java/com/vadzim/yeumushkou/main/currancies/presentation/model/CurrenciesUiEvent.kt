package com.vadzim.yeumushkou.main.currancies.presentation.model

import com.vadzim.yeumushkou.core.presentation.mvi.UiEvent
import com.vadzim.yeumushkou.domain.model.ExchangeRates

internal sealed interface CurrenciesUiEvent : UiEvent {

    sealed interface UI : CurrenciesUiEvent {

        data object InitForm : UI

        data class OnSelectCurrency(val currency: String) : UI

        data class OnStarClick(
            val baseCurrency: String,
            val relatedCurrency: String,
            val isFavorite: Boolean,
        ) : UI
    }

    sealed interface Domain : CurrenciesUiEvent {

        data class ExchangeRatesLoaded(val result: Result<ExchangeRates>) : Domain
    }
}