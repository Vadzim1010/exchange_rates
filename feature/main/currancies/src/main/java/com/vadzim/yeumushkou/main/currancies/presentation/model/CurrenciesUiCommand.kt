package com.vadzim.yeumushkou.main.currancies.presentation.model

import com.vadzim.yeumushkou.core.presentation.mvi.UiCommand

internal sealed interface CurrenciesUiCommand : UiCommand {

    data object ShowErrorDialog : CurrenciesUiCommand
}
