package com.vadzim.yeumushkou.main.currancies.presentation.model

import androidx.compose.runtime.Immutable
import com.vadzim.yeumushkou.core.presentation.mvi.UiState

@Immutable
internal data class CurrenciesUiState(
    val base: String,
    val rates: List<Rate>,
) : UiState {

    @Immutable
    data class Rate(
        val currency: String,
        val value: Double,
        val isFavorite: Boolean
    )
}