package com.vadzim.yeumushkou.core.presentation.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    isPasswordField: Boolean = false,
    isPasswordVisible: Boolean = isPasswordField,
    label: String,
    isError: State<Boolean>,
    value: State<String>,
    onInputChanged: (String) -> Unit,
    onTrailingIconClick: (() -> Unit)? = null
) {
    val visualTransformation = if (isPasswordField && isPasswordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    val trailingIcon: @Composable (() -> Unit)? = if (isPasswordField) {
        { LockPasswordIcon { onTrailingIconClick?.invoke() } }
    } else {
        null
    }

    OutlinedTextField(
        value = value.value,
        onValueChange = { onInputChanged(it) },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        label = { Text(text = label) },
        visualTransformation = visualTransformation,
        maxLines = 1,
        trailingIcon = trailingIcon,
        isError = isError.value
    )
}

@Composable
internal fun LockPasswordIcon(onIconClick: () -> Unit) {
    Icon(
        imageVector = Icons.Default.Lock,
        contentDescription = "emailIcon",
        Modifier.clickable { onIconClick() }
    )
}