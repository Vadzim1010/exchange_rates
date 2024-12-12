package com.vadzim.yeumushkou.domain.model

data class ExchangeRates(
    val baseCurrency: Currency,
    val rates: List<Rate>,
)