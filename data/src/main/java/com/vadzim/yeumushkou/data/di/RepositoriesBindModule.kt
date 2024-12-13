package com.vadzim.yeumushkou.data.di

import com.vadzim.yeumushkou.data.repository.ExchangeRatesLocalRepositoryImpl
import com.vadzim.yeumushkou.data.repository.ExchangeRatesRemoteRepositoryImpl
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesLocalRepository
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesRemoteRepository
import dagger.Binds
import dagger.Module

@Module
internal interface RepositoriesBindModule {

    @Binds
    fun bindExchangeRatesRemoteRepository(repository: ExchangeRatesRemoteRepositoryImpl): ExchangeRatesRemoteRepository

    @Binds
    fun bindExchangeRatesLocalRepository(repository: ExchangeRatesLocalRepositoryImpl): ExchangeRatesLocalRepository

}