package com.vadzim.yeumushkou.favorite.di

import androidx.lifecycle.ViewModel
import com.vadzim.yeumushkou.core.di.ViewModelClassKey
import com.vadzim.yeumushkou.favorite.presentation.viewmodel.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface FavoritesViewModelBindModule {

    @Binds
    @[IntoMap ViewModelClassKey(FavoritesViewModel::class)]
    fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel
}