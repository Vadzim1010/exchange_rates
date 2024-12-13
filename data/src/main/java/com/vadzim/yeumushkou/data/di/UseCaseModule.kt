package com.vadzim.yeumushkou.data.di

import com.vadzim.yeumushkou.domain.usecase.GetFavoritesRatesUseCase
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesLocalRepository
import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesRemoteRepository
import com.vadzim.yeumushkou.domain.usecase.GetExchangeRatesUseCase
import com.vadzim.yeumushkou.domain.usecase.UpdateFavoritesUseCase
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

    @Provides
    fun provideUpdateFavoritesUseCase(
        localRepository: ExchangeRatesLocalRepository,
    ): UpdateFavoritesUseCase {
        return UpdateFavoritesUseCase(localRepository)
    }

    @Provides
    fun provideGetFavoritesRatesUseCase(
        remoteRepository: ExchangeRatesRemoteRepository,
        localRepository: ExchangeRatesLocalRepository,
    ): GetFavoritesRatesUseCase {
        return GetFavoritesRatesUseCase(remoteRepository, localRepository)
    }

}