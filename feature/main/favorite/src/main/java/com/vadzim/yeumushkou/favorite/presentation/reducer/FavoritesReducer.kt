package com.vadzim.yeumushkou.favorite.presentation.reducer

import com.vadzim.yeumushkou.core.presentation.mvi.reducer.Reducer
import com.vadzim.yeumushkou.favorite.presentation.handeler.FavoritesSideEffectHandler
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesEvent
import com.vadzim.yeumushkou.favorite.presentation.mapper.map
import javax.inject.Inject
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesSideEffect as SideEffect
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesUiCommand as Command
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesEvent as Event
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesUiState as State

internal class FavoritesReducer @Inject constructor(
    override val sideEffectHandler: FavoritesSideEffectHandler,
) : Reducer<State, Event, SideEffect, Command> {

    override suspend fun reduce(state: State, event: Event): State {
        return when (event) {
            is Event.UI.InitForm -> reduceInitForm(state)
            is Event.UI.OnStarClick -> reduceOnStarClick(event, state)
            is FavoritesEvent.Domain.FavoritesRatesLoaded -> reduceFavoritesRatesLoaded(event, state)
        }
    }

    override fun initialEvents(): List<Event> {
        return listOf(Event.UI.InitForm)
    }

    override fun initialState(): State {
        return State(
            rates = emptyList(),
        )
    }

    private suspend fun reduceInitForm(state: State): State {
        sideEffectHandler.onDomainSideEffect(
            SideEffect.Domain.LoadFavorites
        )
        return state
    }

    private suspend fun reduceFavoritesRatesLoaded(event: Event.Domain.FavoritesRatesLoaded, state: State): State {
        return event.result.fold(
            onSuccess = { result -> state.copy(rates = result.map()) },
            onFailure = {
                sideEffectHandler.onUiSideEffect(SideEffect.Ui.ShowErrorDialog)
                state
            }
        )
    }


    private suspend fun reduceOnStarClick(event: Event.UI.OnStarClick, state: State): State {
        sideEffectHandler.onDomainSideEffect(
            SideEffect.Domain.UpdateFavorites(
                baseCurrency = event.baseCurrency,
                relatedCurrency = event.relatedCurrency,
                isFavorite = event.isFavorite,
            )
        )

        val items = state.rates.filter { rate -> rate.baseCurrency == event.relatedCurrency }

        return state.copy(rates = items)
    }

}