package com.vadzim.yeumushkou.data.api

import com.vadzim.yeumushkou.data.dto.ExchangeRatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ExchangeRatesApiService {

    @GET("latest")
    suspend fun getLatestRates(
        @Query("symbols") symbols: String,
        @Query("base") base: String,
    ): ExchangeRatesResponse
}