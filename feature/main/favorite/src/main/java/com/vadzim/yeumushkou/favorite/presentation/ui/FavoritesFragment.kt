package com.vadzim.yeumushkou.favorite.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.vadzim.yeumushkou.core.di.viewmodel.ViewModelFactory
import com.vadzim.yeumushkou.core.presentation.ui.ExchangeRatesTheme
import com.vadzim.yeumushkou.favorite.di.FavoritesComponentViewModel
import com.vadzim.yeumushkou.favorite.presentation.viewmodel.FavoritesViewModel
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: FavoritesViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get<FavoritesComponentViewModel>().component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner))

            setContent {
                ExchangeRatesTheme {
                    FavoritesScreen(viewModel)
                }
            }
        }
    }
}