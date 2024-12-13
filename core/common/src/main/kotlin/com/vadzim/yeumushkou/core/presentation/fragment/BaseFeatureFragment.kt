package com.vadzim.yeumushkou.core.presentation.fragment

import androidx.annotation.LayoutRes
import com.vadzim.yeumushkou.core.R

abstract class BaseFeatureFragment(
    @LayoutRes layoutRes: Int = R.layout.feature_nav_host_fragment
) : BaseNavigationFragment(layoutRes), FragmentInjector