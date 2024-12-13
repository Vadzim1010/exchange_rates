package com.vadzim.yeumushkou.main.currancies.di

import com.vadzim.yeumushkou.core.di.FeatureScope
import com.vadzim.yeumushkou.main.currancies.di.module.CurrenciesModule
import com.vadzim.yeumushkou.main.currancies.presentation.ui.CurrenciesFragment
import dagger.Component

@FeatureScope
@Component(modules = [CurrenciesModule::class], dependencies = [CurrenciesDependencies::class])
internal interface CurrenciesComponent {

    fun inject(fragment: CurrenciesFragment)

    @Component.Factory
    interface Factory {

        fun create(dependencies: CurrenciesDependencies): CurrenciesComponent
    }
}