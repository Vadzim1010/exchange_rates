package com.vadzim.yeumushkou.main.root.di

import com.vadzim.yeumushkou.core.di.FeatureScope
import com.vadzim.yeumushkou.main.root.di.module.MainModule
import com.vadzim.yeumushkou.main.root.presentation.ui.MainFragment
import dagger.Component

@FeatureScope
@Component(modules = [MainModule::class], dependencies = [MainDependencies::class])
internal interface MainComponent {

    fun inject(fragment: MainFragment)

    @Component.Factory
    interface Factory {

        fun create(dependencies: MainDependencies): MainComponent
    }
}