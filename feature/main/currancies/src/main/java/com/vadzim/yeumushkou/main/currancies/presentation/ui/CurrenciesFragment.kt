package com.vadzim.yeumushkou.main.currancies.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.vadzim.yeumushkou.core.di.viewmodel.ViewModelFactory
import com.vadzim.yeumushkou.core.presentation.fragment.BaseFragment
import com.vadzim.yeumushkou.core.presentation.ui.ExchangeRatesTheme
import com.vadzim.yeumushkou.main.currancies.di.CurrenciesComponentViewModel
import com.vadzim.yeumushkou.main.currancies.presentation.viewmodel.CurrenciesViewModel
import javax.inject.Inject

class CurrenciesFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: CurrenciesViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get<CurrenciesComponentViewModel>().component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner))

            setContent {
                ExchangeRatesTheme {
                    CurrenciesScreen(viewModel)
                }
            }
        }
    }
}