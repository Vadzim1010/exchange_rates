package com.vadzim.yeumushkou.core.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.vadzim.yeumushkou.core.R

abstract class BaseNavigationFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    val navController: NavController by lazy { navHostFragment.findNavController() }

    private val navHostFragment: NavHostFragment by lazy {
        childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    abstract fun setNavigationGraph()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationGraph()
    }
}