package com.vadzim.yeumushkou.data.di

import dagger.Module

@Module(
    includes = [
        RepositoriesBindModule::class,
        ApiServiceModule::class,
        UseCaseModule::class,
        DatabaseModule::class,
    ]
)
interface DataLayerModule