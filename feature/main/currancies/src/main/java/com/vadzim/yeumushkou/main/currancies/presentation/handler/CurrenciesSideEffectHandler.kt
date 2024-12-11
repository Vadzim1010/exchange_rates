package com.vadzim.yeumushkou.main.currancies.presentation.handler

import com.vadzim.yeumushkou.core.presentation.mvi.reducer.SideEffectHandler
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesRemoteRepository
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiCommand
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesSideEffect as SideEffect

internal class CurrenciesSideEffectHandler @Inject constructor(
    private val repository: ExchangeRatesRemoteRepository
) : SideEffectHandler<CurrenciesUiEvent.Domain, CurrenciesUiCommand, SideEffect>() {

    override fun handleDomainSideEffect(sideEffect: SideEffect): Flow<CurrenciesUiEvent.Domain> {
        return when (sideEffect) {
            is SideEffect.Domain.LoadExchangeRates -> handleLoadExchangeRates(sideEffect)
            else -> error("Not implemented")
        }
    }

    override fun handleUiSideEffect(sideEffect: SideEffect): CurrenciesUiCommand {
        return when (sideEffect) {
            is SideEffect.Ui.ShowErrorDialog -> CurrenciesUiCommand.ShowErrorDialog
            else -> error("Not implemented")
        }
    }

    private fun handleLoadExchangeRates(sideEffect: SideEffect.Domain.LoadExchangeRates): Flow<CurrenciesUiEvent.Domain> {
        return flow {
            repository.getLatestExchangeRates(
                baseCurrency = sideEffect.baseCurrency,
                conversionCurrencies = sideEffect.conversionCurrencies,
            ).also { result -> emit(result) }
        }
            .map(CurrenciesUiEvent.Domain::ExchangeRatesLoaded)
            .flowOn(Dispatchers.IO)
    }
}