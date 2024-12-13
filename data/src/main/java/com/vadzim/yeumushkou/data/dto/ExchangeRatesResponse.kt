package com.vadzim.yeumushkou.data.dto

import com.google.gson.annotations.SerializedName
import com.vadzim.yeumushkou.domain.model.Currency

internal data class ExchangeRatesResponse(

    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Map<Currency, Double>
)