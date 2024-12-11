package com.vadzim.yeumushkou.core.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import com.vadzim.yeumushkou.core.presentation.fragment.BaseNavigationFragment

/**
 * Sets passed navigation graph resource and common navigation graphs to navigation controller
 *
 * @receiver BaseNavigationActivity - activity, the controller of which will have a graph installed
 * @param graphResId Int - navigation graph resource id
 * @param startDestinationArgs Bundle? - added arguments
 * @param startDestinationId Int? - start destination id
 */
fun BaseNavigationFragment.setGraph(
    @NavigationRes graphResId: Int,
    startDestinationArgs: Bundle? = null,
    @IdRes startDestinationId: Int? = null
) {
    val graph = navController.navInflater.inflate(graphResId)
    startDestinationId?.let { graph.setStartDestination(it) }
    navController.setGraph(graph, startDestinationArgs)
}

/**
 * Navigates with passed direction
 *
 * @receiver Fragment - fragment from which will be navigation
 * @param directions NavDirections - navigation direction
 */
fun Fragment.navigateFragment(directions: NavDirections) {
    getCurrentNode().getAction(directions.actionId)
        ?.let { findNavController().navigate(directions) }
}

/**
 * Navigates with passed route
 *
 * @receiver Fragment - fragment from which will be navigation
 * @param route String - route to navigation
 */
@SuppressLint("RestrictedApi")
fun Fragment.navigateFragment(route: String) {
    findNavController().findDestination(route)
        ?.let { findNavController().navigate(route) }
}

/**
 * Navigates with passed action
 *
 * @receiver Fragment - fragment from which will be navigation
 * @param action Int - navigation action id
 * @param args Bundle? - parameters that will be transmitted during navigation
 * @param navigatorExtras Navigator.Extras? - Navigator specific behavior options
 */
fun Fragment.navigateFragment(
    action: Int,
    args: Bundle? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    getCurrentNode().getAction(action)?.let {
        findNavController().navigate(action, args, null, navigatorExtras)
    }
}

private fun Fragment.getCurrentNode() =
    findNavController().let { it.currentBackStackEntry?.destination ?: it.graph }