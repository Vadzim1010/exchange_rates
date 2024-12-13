package com.vadzim.yeumushkou.main.currancies.presentation.model

import com.vadzim.yeumushkou.core.presentation.mvi.Event
import com.vadzim.yeumushkou.domain.model.ExchangeRates

internal sealed interface CurrenciesEvent : Event {

    sealed interface UI : CurrenciesEvent {

        data object InitForm : UI

        data class OnSelectCurrency(val currency: String) : UI

        data class OnStarClick(
            val baseCurrency: String,
            val relatedCurrency: String,
            val isFavorite: Boolean,
        ) : UI
    }

    sealed interface Domain : CurrenciesEvent {

        data class ExchangeRatesLoaded(val result: Result<ExchangeRates>) : Domain
    }
}