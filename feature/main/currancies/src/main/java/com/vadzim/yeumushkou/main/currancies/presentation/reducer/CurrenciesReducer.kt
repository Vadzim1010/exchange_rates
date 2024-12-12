package com.vadzim.yeumushkou.main.currancies.presentation.reducer

import com.vadzim.yeumushkou.core.presentation.mvi.reducer.Reducer
import com.vadzim.yeumushkou.domain.model.Currency
import com.vadzim.yeumushkou.main.currancies.presentation.handler.CurrenciesSideEffectHandler
import com.vadzim.yeumushkou.main.currancies.presentation.mapper.map
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiEvent
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiState
import javax.inject.Inject
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesSideEffect as SideEffect
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiCommand as Command
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiEvent as Event
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiState as State

internal class CurrenciesReducer @Inject constructor(
    override val sideEffectHandler: CurrenciesSideEffectHandler,
) : Reducer<State, Event, SideEffect, Command> {

    override suspend fun reduce(state: State, event: Event): State {
        return when (event) {
            is Event.UI.InitForm -> reduceInitForm(state)
            is Event.Domain.ExchangeRatesLoaded -> reduceExchangeRatesLoaded(event, state)
            is Event.UI.OnSelectCurrency -> reduceOnSelectCurrency(event, state)
            is Event.UI.OnStarClick -> reduceOnStarClick(event, state)
        }
    }

    override fun initialEvents(): List<CurrenciesUiEvent> {
        return listOf(Event.UI.InitForm)
    }

    override fun initialState(): CurrenciesUiState {
        return CurrenciesUiState(
            base = Currency.EUR.name,
            rates = emptyList(),
        )
    }

    private suspend fun reduceInitForm(state: State): State {
        sideEffectHandler.onDomainSideEffect(
            SideEffect.Domain.LoadExchangeRates(
                baseCurrency = Currency.valueOf(state.base),
                conversionCurrencies = Currency.entries.filterNot { it.name == state.base }
            )
        )
        return state
    }

    private suspend fun reduceExchangeRatesLoaded(event: Event.Domain.ExchangeRatesLoaded, state: State): State {
        return event.result.fold(
            onSuccess = { result -> state.copy(base = result.baseCurrency.name, rates = result.rates.map()) },
            onFailure = {
                sideEffectHandler.onUiSideEffect(SideEffect.Ui.ShowErrorDialog)
                state
            }
        )
    }

    private suspend fun reduceOnSelectCurrency(event: Event.UI.OnSelectCurrency, state: State): State {
        sideEffectHandler.onDomainSideEffect(
            SideEffect.Domain.LoadExchangeRates(
                baseCurrency = Currency.valueOf(event.currency),
                conversionCurrencies = Currency.entries.filterNot { it.name == event.currency }
            )
        )
        return state.copy(base = event.currency)
    }

    private suspend fun reduceOnStarClick(event: Event.UI.OnStarClick, state: State): State {
        sideEffectHandler.onDomainSideEffect(
            SideEffect.Domain.UpdateFavorites(
                baseCurrency = event.baseCurrency,
                relatedCurrency = event.relatedCurrency,
                isFavorite = event.isFavorite,
            )
        )
        val items = state.rates.map { rate ->
            rate.copy(isFavorite = !rate.isFavorite).takeIf { it.baseCurrency == event.relatedCurrency } ?: rate
        }

        return state.copy(rates = items)
    }

}