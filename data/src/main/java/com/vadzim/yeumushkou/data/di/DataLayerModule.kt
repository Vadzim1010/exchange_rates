package com.vadzim.yeumushkou.data.di

import dagger.Module

@Module(
    includes = [
        RepositoriesBindModule::class,
        ApiServiceModule::class,
    ]
)
interface DataLayerModule