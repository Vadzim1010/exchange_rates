package com.vadzim.yeumushkou.favorite.presentation.handeler

import com.vadzim.yeumushkou.core.presentation.mvi.reducer.SideEffectHandler
import com.vadzim.yeumushkou.domain.usecase.GetFavoritesRatesUseCase
import com.vadzim.yeumushkou.domain.usecase.UpdateFavoritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesSideEffect as SideEffect
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesUiCommand as Command
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesEvent as Event

internal class FavoritesSideEffectHandler @Inject constructor(
    private val getFavoritesRatesUseCase: GetFavoritesRatesUseCase,
    private val updateFavoritesUseCase: UpdateFavoritesUseCase,
) : SideEffectHandler<Event.Domain, Command, SideEffect>() {

    override suspend fun handleDomainSideEffect(sideEffect: SideEffect): Flow<Event.Domain> {
        return when (sideEffect) {
            is SideEffect.Domain.UpdateFavorites -> handUpdateFavorites(sideEffect)
            is SideEffect.Domain.LoadFavorites -> handleLoadFavoritesRates()
            else -> error("Not implemented")
        }
    }

    override suspend fun handleUiSideEffect(sideEffect: SideEffect): Command {
        return when (sideEffect) {
            is SideEffect.Ui.ShowErrorDialog -> Command.ShowErrorDialog
            else -> error("Not implemented")
        }
    }

    private suspend fun handleLoadFavoritesRates(): Flow<Event.Domain> {
        return getFavoritesRatesUseCase()
            .map(Event.Domain::FavoritesRatesLoaded)
            .flowOn(Dispatchers.IO)
    }

    private suspend fun handUpdateFavorites(sideEffect: SideEffect.Domain.UpdateFavorites): Flow<Event.Domain> {
        updateFavoritesUseCase(sideEffect.baseCurrency, sideEffect.relatedCurrency, sideEffect.isFavorite)
        return emptyFlow()
    }

}