package com.vadzim.yeumushkou.main.root.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vadzim.yeumushkou.core.di.deps.findComponentDependencies

internal class MainComponentViewModel(application: Application) : AndroidViewModel(application) {

    val component: MainComponent by lazy {
        DaggerMainComponent.factory().create(application.findComponentDependencies())
    }

    init {
        component
    }
}