package com.vadzim.yeumushkou.main.currancies.presentation.handler

import com.vadzim.yeumushkou.core.presentation.mvi.reducer.SideEffectHandler
import com.vadzim.yeumushkou.domain.usecase.GetExchangeRatesUseCase
import com.vadzim.yeumushkou.domain.usecase.UpdateFavoritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesSideEffect as SideEffect
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiCommand as Command
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesEvent as Event

internal class CurrenciesSideEffectHandler @Inject constructor(
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase,
    private val updateFavoritesUseCase: UpdateFavoritesUseCase,
) : SideEffectHandler<Event.Domain, Command, SideEffect>() {

    override suspend fun handleDomainSideEffect(sideEffect: SideEffect): Flow<Event.Domain> {
        return when (sideEffect) {
            is SideEffect.Domain.LoadExchangeRates -> handleLoadExchangeRates(sideEffect)
            is SideEffect.Domain.UpdateFavorites -> handUpdateFavorites(sideEffect)
            else -> error("Not implemented")
        }
    }

    override suspend fun handleUiSideEffect(sideEffect: SideEffect): Command {
        return when (sideEffect) {
            is SideEffect.Ui.ShowErrorDialog -> Command.ShowErrorDialog
            else -> error("Not implemented")
        }
    }

    private suspend fun handleLoadExchangeRates(sideEffect: SideEffect.Domain.LoadExchangeRates): Flow<Event.Domain> {
        return getExchangeRatesUseCase(sideEffect.baseCurrency, sideEffect.conversionCurrencies)
            .map(Event.Domain::ExchangeRatesLoaded)
            .flowOn(Dispatchers.IO)
    }

    private suspend fun handUpdateFavorites(sideEffect: SideEffect.Domain.UpdateFavorites): Flow<Event.Domain> {
        updateFavoritesUseCase(sideEffect.baseCurrency, sideEffect.relatedCurrency, sideEffect.isFavorite)
        return emptyFlow()
    }
}