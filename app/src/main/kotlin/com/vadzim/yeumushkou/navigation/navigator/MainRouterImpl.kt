package com.vadzim.yeumushkou.navigation.navigator

import com.vadzim.yeumushkou.main.R
import com.vadzim.yeumushkou.main.root.router.MainRouter
import com.vadzim.yeumushkou.navigation.NavControllerManager
import javax.inject.Inject

internal class MainRouterImpl @Inject constructor(
    private val navControllerManager: NavControllerManager,
) : MainRouter {

    override fun goCurrencies() {
        navControllerManager.featureNavController.navigate(R.id.action_favoritesFragment_to_currenciesFragment)
    }

    override fun goFavorites() {
        navControllerManager.featureNavController.navigate(R.id.action_currenciesFragment_to_favoritesFragment)
    }

}