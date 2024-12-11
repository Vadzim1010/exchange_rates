package com.vadzim.yeumushkou.main.currancies.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vadzim.yeumushkou.core.presentation.ui.BgDefault
import com.vadzim.yeumushkou.core.presentation.ui.BgHeader
import com.vadzim.yeumushkou.core.presentation.ui.Primary
import com.vadzim.yeumushkou.core.presentation.ui.Secondary
import com.vadzim.yeumushkou.core.presentation.ui.TextDefault
import com.vadzim.yeumushkou.core.presentation.ui.Yellow
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiState
import com.vadzim.yeumushkou.main.currancies.presentation.model.CurrenciesUiEvent
import com.vadzim.yeumushkou.main.currancies.presentation.viewmodel.CurrenciesViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
internal fun CurrenciesScreen(viewModel: CurrenciesViewModel) {

    val state = viewModel.stateFlow.collectAsState()
    val events: MutableState<CurrenciesUiEvent?> = remember { mutableStateOf(value = null) }

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
    state: State<CurrenciesUiState>,
    eventListener: (CurrenciesUiEvent) -> Unit
) {
    val selectedCurrency = remember { derivedStateOf { state.value.base } }
    val rates = remember { derivedStateOf { state.value.rates } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Currencies", fontWeight = FontWeight.Bold, fontSize = 22.sp) },
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
        ) {
            Box(
                modifier = Modifier
                    .background(BgHeader)
                    .padding(bottom = 8.dp)
            ) {


                // Dropdown for currency selection
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var expanded by remember { mutableStateOf(false) }
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        OutlinedTextField(
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                color = TextDefault,
                            ),
                            value = selectedCurrency.value,
                            onValueChange = {},
                            readOnly = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Secondary,
                                unfocusedBorderColor = Secondary,
                                focusedContainerColor = BgDefault,
                                unfocusedContainerColor = BgDefault
                            ),
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()

                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(BgDefault),
                        ) {
                            rates.value.forEach { rate ->
                                DropdownMenuItem(
                                    text = { Text(rate.currency) },
                                    onClick = { eventListener(CurrenciesUiEvent.UI.OnSelectCurrency(rate.currency)) },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .border(1.dp, Secondary, RoundedCornerShape(8.dp))
                            .background(BgDefault)
                            .clickable(onClick = { println("Icon clicked!") }),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Filter",
                            tint = Primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                rates.value.forEach { rate ->
                    CurrencyItem(rate = rate)
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
internal fun CurrencyItem(rate: CurrenciesUiState.Rate) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = rate.currency,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = TextDefault,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = String.format("%.6f", rate.rate),
                fontWeight = FontWeight.Bold,
                color = TextDefault,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = { /* Handle favorite toggle */ }) {
                Icon(
                    imageVector = if (rate.isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = null,
                    tint = if (rate.isFavorite) Yellow else Secondary
                )
            }
        }
    }
}