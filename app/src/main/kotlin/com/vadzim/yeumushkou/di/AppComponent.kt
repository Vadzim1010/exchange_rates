package com.vadzim.yeumushkou.di

import android.content.Context
import com.vadzim.yeumushkou.MainActivity
import com.vadzim.yeumushkou.core.di.AppScope
import com.vadzim.yeumushkou.core.di.deps.ComponentDependenciesProvider
import com.vadzim.yeumushkou.main.currancies.di.CurrenciesDependencies
import com.vadzim.yeumushkou.main.root.di.MainDependencies
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
internal interface AppComponent :
    MainDependencies,
    CurrenciesDependencies {

    val dependencies: ComponentDependenciesProvider

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}