package com.vadzim.yeumushkou.main.currancies.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vadzim.yeumushkou.core.di.deps.findComponentDependencies

internal class CurrenciesComponentViewModel(application: Application) : AndroidViewModel(application) {

    val component: CurrenciesComponent by lazy {
        DaggerCurrenciesComponent.factory().create(
            dependencies = application.findComponentDependencies()
        )
    }
}