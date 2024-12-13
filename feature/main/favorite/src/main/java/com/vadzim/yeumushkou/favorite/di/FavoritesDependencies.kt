package com.vadzim.yeumushkou.favorite.di

import com.vadzim.yeumushkou.core.di.deps.ComponentDependencies
import com.vadzim.yeumushkou.core.presentation.navigation.CommonRouter
import com.vadzim.yeumushkou.domain.usecase.GetFavoritesRatesUseCase
import com.vadzim.yeumushkou.domain.usecase.UpdateFavoritesUseCase

interface FavoritesDependencies : ComponentDependencies {

    val getFavoritesRatesUseCase: GetFavoritesRatesUseCase
    val updateFavoritesUseCase: UpdateFavoritesUseCase
    val commonRouter: CommonRouter

}