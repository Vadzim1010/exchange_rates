package com.vadzim.yeumushkou.favorite.presentation.model

import com.vadzim.yeumushkou.core.presentation.mvi.Event
import com.vadzim.yeumushkou.domain.model.FavoriteRate

internal sealed interface FavoritesEvent : Event {

    sealed interface UI : FavoritesEvent {

        data object InitForm : UI

        data class OnStarClick(
            val baseCurrency: String,
            val relatedCurrency: String,
            val isFavorite: Boolean,
        ) : UI
    }

    sealed interface Domain : FavoritesEvent {

        data class FavoritesRatesLoaded(val result: Result<List<FavoriteRate>>) : Domain
    }

}