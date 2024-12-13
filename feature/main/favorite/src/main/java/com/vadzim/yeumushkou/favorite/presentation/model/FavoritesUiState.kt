package com.vadzim.yeumushkou.favorite.presentation.model

import androidx.compose.runtime.Immutable
import com.vadzim.yeumushkou.core.presentation.mvi.UiState
import com.vadzim.yeumushkou.core.presentation.state.CurrencyItemState

@Immutable
internal data class FavoritesUiState(
    val rates: List<CurrencyItemState>,
) : UiState