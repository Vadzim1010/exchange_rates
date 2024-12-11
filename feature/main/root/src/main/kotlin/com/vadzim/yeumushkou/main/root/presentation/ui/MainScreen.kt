package com.vadzim.yeumushkou.main.root.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

internal const val HOME_TAG = "home"
internal const val SEARCH_TAG = "search"

@Composable
internal fun MainScreen(onItemClicked: (String) -> Unit) {
    val items = listOf(HOME_TAG, SEARCH_TAG)
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = item
                    )
                },
                label = { Text(item) },
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    onItemClicked(items[selectedIndex])
                }
            )
        }
    }
}