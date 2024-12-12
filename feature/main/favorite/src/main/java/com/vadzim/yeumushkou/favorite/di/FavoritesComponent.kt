package com.vadzim.yeumushkou.favorite.di

import com.vadzim.yeumushkou.core.di.FeatureScope
import com.vadzim.yeumushkou.favorite.presentation.ui.FavoritesFragment
import dagger.Component

@FeatureScope
@Component(modules = [FavoritesModule::class], dependencies = [FavoritesDependencies::class])
internal interface FavoritesComponent {

    fun inject(fragment: FavoritesFragment)

    @Component.Factory
    interface Factory {

        fun create(dependencies: FavoritesDependencies): FavoritesComponent
    }
}