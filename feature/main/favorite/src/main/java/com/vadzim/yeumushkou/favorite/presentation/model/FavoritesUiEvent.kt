package com.vadzim.yeumushkou.favorite.presentation.model

import com.vadzim.yeumushkou.core.presentation.mvi.UiEvent
import com.vadzim.yeumushkou.domain.model.FavoriteRate

internal sealed interface FavoritesUiEvent : UiEvent {

    sealed interface UI : FavoritesUiEvent {

        data object InitForm : UI

        data class OnStarClick(
            val baseCurrency: String,
            val relatedCurrency: String,
            val isFavorite: Boolean,
        ) : UI
    }

    sealed interface Domain : FavoritesUiEvent {

        data class FavoritesRatesLoaded(val result: Result<List<FavoriteRate>>) : Domain
    }

}