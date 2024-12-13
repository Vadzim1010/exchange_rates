package com.vadzim.yeumushkou.data.di

import com.vadzim.yeumushkou.data.api.ExchangeRatesApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class ApiServiceModule {

    @Provides
    fun provideExchangeRatesApiService(retrofit: Retrofit): ExchangeRatesApiService =
        retrofit.create(ExchangeRatesApiService::class.java)
}