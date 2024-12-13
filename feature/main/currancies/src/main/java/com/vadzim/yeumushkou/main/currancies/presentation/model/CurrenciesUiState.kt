package com.vadzim.yeumushkou.main.currancies.presentation.model

import androidx.compose.runtime.Immutable
import com.vadzim.yeumushkou.core.presentation.mvi.UiState
import com.vadzim.yeumushkou.core.presentation.state.CurrencyItemState

@Immutable
internal data class CurrenciesUiState(
    val base: String,
    val rates: List<CurrencyItemState>,
) : UiState