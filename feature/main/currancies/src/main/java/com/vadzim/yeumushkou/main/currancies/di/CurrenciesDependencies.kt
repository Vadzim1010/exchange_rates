package com.vadzim.yeumushkou.main.currancies.di

import android.content.Context
import com.vadzim.yeumushkou.core.di.deps.ComponentDependencies
import com.vadzim.yeumushkou.core.presentation.navigation.CommonRouter
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesRemoteRepository

interface CurrenciesDependencies : ComponentDependencies {

    val context: Context
    val repository: ExchangeRatesRemoteRepository
    val commonRouter: CommonRouter
}