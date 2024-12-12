package com.vadzim.yeumushkou.favorite.presentation.viewmodel

import com.vadzim.yeumushkou.core.presentation.mvi.viewmodel.MviViewModel
import com.vadzim.yeumushkou.core.presentation.navigation.CommonRouter
import com.vadzim.yeumushkou.favorite.presentation.reducer.FavoritesReducer
import javax.inject.Inject
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesSideEffect as SideEffect
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesUiCommand as Command
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesUiEvent as Event
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesUiState as State

internal class FavoritesViewModel @Inject constructor(
    reducer: FavoritesReducer,
    private val commonRouter: CommonRouter,
) : MviViewModel<State, Event, SideEffect, Command>(reducer) {

    override fun handleUiCommand(command: Command) {
        when (command) {
            Command.ShowErrorDialog -> commonRouter.showExceptionDialog()
        }
    }

}