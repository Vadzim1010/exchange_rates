package com.vadzim.yeumushkou.core.presentation.ui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vadzim.yeumushkou.core.presentation.state.CurrencyItemState
import com.vadzim.yeumushkou.core.presentation.ui.Secondary
import com.vadzim.yeumushkou.core.presentation.ui.TextDefault
import com.vadzim.yeumushkou.core.presentation.ui.Yellow

@Composable
fun CurrencyItem(
    state: CurrencyItemState,
    onStarClick: (state: CurrencyItemState) -> Unit,
) {
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
            val currencies = listOf(state.baseCurrency, state.relatedCurrency)
                .filterNotNull()
                .joinToString("/")

            Text(
                text = currencies,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = TextDefault,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = state.value,
                fontWeight = FontWeight.Bold,
                color = TextDefault,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = { onStarClick(state) }) {
                Icon(
                    imageVector = if (state.isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = null,
                    tint = if (state.isFavorite) Yellow else Secondary
                )
            }
        }
    }
}