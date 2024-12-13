package com.vadzim.yeumushkou.main.currancies.presentation.viewmodel

import com.vadzim.yeumushkou.core.presentation.mvi.viewmodel.MviViewModel
import com.vadzim.yeumushkou.core.presentation.navigation.CommonRouter
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiCommand
import com.vadzim.yeumushkou.main.currancies.presentation.reducer.CurrenciesReducer
import javax.inject.Inject
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesSideEffect as SideEffect
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiCommand as Command
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesEvent as Event
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiState as State

internal class CurrenciesViewModel @Inject constructor(
    reducer: CurrenciesReducer,
    private val commonRouter: CommonRouter,
) : MviViewModel<State, Event, SideEffect, Command>(reducer) {

    override fun handleUiCommand(command: Command) {
        when (command) {
            CurrenciesUiCommand.ShowErrorDialog -> commonRouter.showExceptionDialog()
        }
    }

}