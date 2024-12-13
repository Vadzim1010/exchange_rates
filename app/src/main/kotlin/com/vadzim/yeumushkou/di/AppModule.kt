package com.vadzim.yeumushkou.di

import com.vadzim.yeumushkou.core.network.di.NetworkModule
import com.vadzim.yeumushkou.data.di.DataLayerModule
import dagger.Module

@Module(
    includes = [
        DependenciesBindModule::class,
        NetworkModule::class,
        AppNavigationBindsModule::class,
        DataLayerModule::class,
    ]
)
internal interface AppModule