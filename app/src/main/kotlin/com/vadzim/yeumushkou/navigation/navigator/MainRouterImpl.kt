package com.vadzim.yeumushkou.navigation.navigator

import com.vadzim.yeumushkou.main.root.router.MainRouter
import com.vadzim.yeumushkou.navigation.NavControllerManager
import javax.inject.Inject

internal class MainRouterImpl @Inject constructor(
    private val navControllerManager: NavControllerManager,
) : MainRouter {

    override fun goCurrencies() {
//        navControllerManager.featureNavController.navigate(com.vadzim.yeumushkou.main.R.id.action_testFragment11_to_testFragment1)
    }

    override fun goFavorites() {
//        navControllerManager.featureNavController.navigate(com.vadzim.yeumushkou.main.R.id.action_testFragment1_to_testFragment11)
    }

}