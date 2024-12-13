package com.vadzim.yeumushkou.main.root.di

import android.content.Context
import com.vadzim.yeumushkou.core.di.deps.ComponentDependencies
import com.vadzim.yeumushkou.main.root.router.MainRouter

interface MainDependencies : ComponentDependencies {

    val context: Context
    val mainRouter: MainRouter
}