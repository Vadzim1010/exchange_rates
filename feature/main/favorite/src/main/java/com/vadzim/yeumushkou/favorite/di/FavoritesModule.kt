package com.vadzim.yeumushkou.favorite.di

import com.vadzim.yeumushkou.core.di.viewmodel.ViewModelFactoryModule
import dagger.Module

@Module(
    includes = [
        FavoritesViewModelBindModule::class,
        ViewModelFactoryModule::class,
    ]
)
internal interface FavoritesModule