package com.vadzim.yeumushkou

import android.app.Application
import android.content.Context
import com.vadzim.yeumushkou.core.di.deps.ComponentDependenciesProvider
import com.vadzim.yeumushkou.core.di.deps.HasComponentDependencies
import com.vadzim.yeumushkou.di.AppComponent
import com.vadzim.yeumushkou.di.DaggerAppComponent

internal class ExchangeRatesApplication : Application(), HasComponentDependencies {

    override val dependencies: ComponentDependenciesProvider
        get() = appComponent.dependencies

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}

internal fun Context.findAppComponent(): AppComponent =
    if (this is ExchangeRatesApplication) appComponent else applicationContext.findAppComponent()