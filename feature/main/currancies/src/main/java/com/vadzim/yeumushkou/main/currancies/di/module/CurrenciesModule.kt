package com.vadzim.yeumushkou.main.currancies.di.module

import com.vadzim.yeumushkou.core.di.viewmodel.ViewModelFactoryModule
import dagger.Module

@Module(
    includes = [
        CurrenciesViewModelBindModule::class,
        ViewModelFactoryModule::class,
    ]
)
internal interface CurrenciesModule