package com.vadzim.yeumushkou.favorite.presentation.model

import com.vadzim.yeumushkou.core.presentation.mvi.UiCommand

internal sealed interface FavoritesUiCommand : UiCommand {

    data object ShowErrorDialog : FavoritesUiCommand

}
