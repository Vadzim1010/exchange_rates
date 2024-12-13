package com.vadzim.yeumushkou.core.presentation.fragment

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        findFragmentInjector().injectFragment(this)
    }
}