package com.vadzim.yeumushkou.di

import com.vadzim.yeumushkou.core.presentation.navigation.CommonRouter
import com.vadzim.yeumushkou.main.root.router.MainRouter
import com.vadzim.yeumushkou.navigation.navigator.CommonRouterImpl
import com.vadzim.yeumushkou.navigation.navigator.MainRouterImpl
import dagger.Binds
import dagger.Module

@Module
internal interface AppNavigationBindsModule {

    @Binds
    fun bindCommonRouter(router: CommonRouterImpl): CommonRouter

    @Binds
    fun bindMainRouter(router: MainRouterImpl): MainRouter
}