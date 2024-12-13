package com.vadzim.yeumushkou.favorite.presentation.model

import com.vadzim.yeumushkou.core.presentation.mvi.SideEffect

internal sealed interface FavoritesSideEffect : SideEffect {

    sealed interface Domain : FavoritesSideEffect {

        data object LoadFavorites : Domain

        data class UpdateFavorites(
            val baseCurrency: String,
            val relatedCurrency: String,
            val isFavorite: Boolean,
        ) : Domain
    }

    sealed interface Ui : FavoritesSideEffect {

        data object ShowErrorDialog : Ui
    }
}