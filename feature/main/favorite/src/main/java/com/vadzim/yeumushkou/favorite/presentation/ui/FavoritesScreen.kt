package com.vadzim.yeumushkou.favorite.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vadzim.yeumushkou.core.presentation.ui.BgDefault
import com.vadzim.yeumushkou.core.presentation.ui.BgHeader
import com.vadzim.yeumushkou.core.presentation.ui.TextDefault
import com.vadzim.yeumushkou.core.presentation.ui.composable.CurrencyItem
import com.vadzim.yeumushkou.favorite.presentation.viewmodel.FavoritesViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesUiEvent as UiEvent
import com.vadzim.yeumushkou.favorite.presentation.model.FavoritesUiState as UiState

@Composable
internal fun FavoritesScreen(viewModel: FavoritesViewModel) {
    val state = viewModel.stateFlow.collectAsState()
    val events: MutableState<UiEvent?> = remember { mutableStateOf(value = null) }

    LaunchedEffect(key1 = viewModel) {
        snapshotFlow(events::value)
            .filterNotNull()
            .onEach { events.value = null }
            .onEach(viewModel::interact)
            .launchIn(this)
    }

    CurrenciesContent(state) { event ->
        events.value = event
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CurrenciesContent(
    state: State<UiState>,
    eventListener: (UiEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorites", fontWeight = FontWeight.Bold, fontSize = 22.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BgHeader,
                    titleContentColor = TextDefault,
                ),
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .background(BgDefault)
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            state.value.rates.forEach { rate ->
                CurrencyItem(state = rate) { itemState ->
                    itemState.relatedCurrency?.let { relatedCurrency ->
                        eventListener(
                            UiEvent.UI.OnStarClick(
                                baseCurrency = itemState.baseCurrency,
                                relatedCurrency = relatedCurrency,
                                isFavorite = itemState.isFavorite,
                            )
                        )
                    }
                }
            }
        }
    }
}