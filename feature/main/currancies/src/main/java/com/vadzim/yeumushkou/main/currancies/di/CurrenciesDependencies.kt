package com.vadzim.yeumushkou.main.currancies.di

import android.content.Context
import com.vadzim.yeumushkou.core.di.deps.ComponentDependencies
import com.vadzim.yeumushkou.core.presentation.navigation.CommonRouter
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesLocalRepository
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesRemoteRepository
import com.vadzim.yeumushkou.domain.usecase.GetExchangeRatesUseCase

interface CurrenciesDependencies : ComponentDependencies {

    val context: Context
    val exchangeRatesRemoteRepository: ExchangeRatesRemoteRepository
    val exchangeRatesLocalRepository: ExchangeRatesLocalRepository
    val commonRouter: CommonRouter
    val getExchangeRatesUseCase: GetExchangeRatesUseCase
}