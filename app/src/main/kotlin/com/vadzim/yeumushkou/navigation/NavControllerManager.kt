package com.vadzim.yeumushkou.navigation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.vadzim.yeumushkou.R
import com.vadzim.yeumushkou.core.di.AppScope
import com.vadzim.yeumushkou.core.presentation.fragment.BaseNavigationFragment
import javax.inject.Inject

@AppScope
internal class NavControllerManager @Inject constructor() : LifecycleEventObserver {

    val appNavController: NavController
        get() = navHostFragment.navController

    val featureNavController: NavController
        get() = (navHostFragment.childFragmentManager.fragments.firstOrNull() as BaseNavigationFragment).navController

    private val navHostFragment: NavHostFragment
        get() = fragmentManager?.findFragmentById(R.id.container) as NavHostFragment

    private var fragmentManager: FragmentManager? = null

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> fragmentManager = (source as FragmentManagerHolder).fragmentManager
            Lifecycle.Event.ON_DESTROY -> fragmentManager = null

            else -> {
                // do nothing
            }
        }
    }
}