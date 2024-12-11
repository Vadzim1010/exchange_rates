package com.vadzim.yeumushkou.main.root.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.vadzim.yeumushkou.core.di.viewmodel.ViewModelFactory
import com.vadzim.yeumushkou.core.presentation.fragment.BaseFeatureFragment
import com.vadzim.yeumushkou.core.presentation.setGraph
import com.vadzim.yeumushkou.main.R
import com.vadzim.yeumushkou.main.root.di.MainComponent
import com.vadzim.yeumushkou.main.root.di.MainComponentViewModel
import com.vadzim.yeumushkou.main.root.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainFragment : BaseFeatureFragment(R.layout.main_fragment) {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels { factory }

    private val component: MainComponent by lazy {
        ViewModelProvider(this).get<MainComponentViewModel>().component
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun injectFragment(fragment: Fragment) {

    }

    override fun setNavigationGraph() {
        setGraph(R.navigation.main_nav_graph)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val composeView = activity?.findViewById<ComposeView>(R.id.compose_view)

        composeView?.setContent {
            MainScreen { tab ->
                when (tab) {
                    HOME_TAG -> viewModel.goCurrencies()
                    SEARCH_TAG -> viewModel.goFavorite()
                    else -> error("Unknown tab: $tab")
                }
            }
        }
    }
}