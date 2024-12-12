package com.vadzim.yeumushkou.di

import com.vadzim.yeumushkou.core.di.ComponentDependenciesClassKey
import com.vadzim.yeumushkou.core.di.deps.ComponentDependencies
import com.vadzim.yeumushkou.favorite.di.FavoritesDependencies
import com.vadzim.yeumushkou.main.currancies.di.CurrenciesDependencies
import com.vadzim.yeumushkou.main.root.di.MainDependencies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface DependenciesBindModule {

    @Binds
    @[IntoMap ComponentDependenciesClassKey(MainDependencies::class)]
    fun bindMainDependencies(dependencies: AppComponent): ComponentDependencies

    @Binds
    @[IntoMap ComponentDependenciesClassKey(CurrenciesDependencies::class)]
    fun bindCurrenciesDependencies(dependencies: AppComponent): ComponentDependencies

    @Binds
    @[IntoMap ComponentDependenciesClassKey(FavoritesDependencies::class)]
    fun bindFavoritesDependencies(dependencies: AppComponent): ComponentDependencies

}