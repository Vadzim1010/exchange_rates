package com.vadzim.yeumushkou.main.root.di.module

import androidx.lifecycle.ViewModel
import com.vadzim.yeumushkou.core.di.ViewModelClassKey
import com.vadzim.yeumushkou.main.root.presentation.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface MainViewModelBindModule {

    @Binds
    @[IntoMap ViewModelClassKey(MainViewModel::class)]
    fun bindAuthorizationViewModel(viewModel: MainViewModel): ViewModel
}