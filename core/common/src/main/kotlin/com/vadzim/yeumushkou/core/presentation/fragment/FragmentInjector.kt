package com.vadzim.yeumushkou.core.presentation.fragment

import androidx.fragment.app.Fragment

interface FragmentInjector {

    fun injectFragment(fragment: Fragment)
}

fun Fragment.findFragmentInjector(): FragmentInjector =
    if (parentFragment is FragmentInjector) parentFragment as FragmentInjector else parentFragment?.findFragmentInjector()
        ?: fragmentInjectorNotFound()

private fun fragmentInjectorNotFound(): Nothing =
    throw IllegalStateException("Parent fragment must implement ${FragmentInjector::class.simpleName}")