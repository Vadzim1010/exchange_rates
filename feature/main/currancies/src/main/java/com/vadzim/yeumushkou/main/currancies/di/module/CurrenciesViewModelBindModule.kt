package com.vadzim.yeumushkou.main.currancies.di.module

import androidx.lifecycle.ViewModel
import com.vadzim.yeumushkou.core.di.ViewModelClassKey
import com.vadzim.yeumushkou.main.currancies.presentation.viewmodel.CurrenciesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface CurrenciesViewModelBindModule {

    @Binds
    @[IntoMap ViewModelClassKey(CurrenciesViewModel::class)]
    fun bindCurrenciesViewModel(viewModel: CurrenciesViewModel): ViewModel
}