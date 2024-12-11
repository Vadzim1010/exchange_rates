package com.vadzim.yeumushkou.navigation.navigator

import androidx.navigation.NavController
import com.vadzim.yeumushkou.R
import com.vadzim.yeumushkou.core.presentation.navigation.CommonRouter
import com.vadzim.yeumushkou.navigation.NavControllerManager
import javax.inject.Inject

internal class CommonRouterImpl @Inject constructor(private val manager: NavControllerManager) : CommonRouter {

    private val navController: NavController get() = manager.appNavController

    override fun showExceptionDialog() {
        navController.navigate(R.id.showExceptionDialog)
    }
}