package com.vadzim.yeumushkou.data.di

import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesLocalRepository
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesRemoteRepository
import com.vadzim.yeumushkou.domain.usecase.GetExchangeRatesUseCase
import dagger.Module
import dagger.Provides

@Module
internal class UseCaseModule {

    @Provides
    fun provideGetExchangeRatesUseCase(
        remoteRepository: ExchangeRatesRemoteRepository,
        localRepository: ExchangeRatesLocalRepository,
    ): GetExchangeRatesUseCase {
        return GetExchangeRatesUseCase(remoteRepository, localRepository)
    }

}