package com.vadzim.yeumushkou.favorite.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vadzim.yeumushkou.core.di.deps.findComponentDependencies

internal class FavoritesComponentViewModel(application: Application) : AndroidViewModel(application) {

    val component: FavoritesComponent by lazy {
        DaggerFavoritesComponent.factory().create(
            dependencies = application.findComponentDependencies()
        )
    }

}