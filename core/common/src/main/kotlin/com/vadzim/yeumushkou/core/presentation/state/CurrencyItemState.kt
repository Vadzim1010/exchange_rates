package com.vadzim.yeumushkou.core.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
data class CurrencyItemState(
    val baseCurrency: String,
    val relatedCurrency: String? = null,
    val value: String,
    val isFavorite: Boolean,
)