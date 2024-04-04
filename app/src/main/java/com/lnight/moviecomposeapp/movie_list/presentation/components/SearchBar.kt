package com.lnight.moviecomposeapp.movie_list.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: String,
    onSearch: () -> Unit,
    onInputText: (String) -> Unit
) {
    val backgroundColor = MaterialTheme.colorScheme.surface
    val outlineColor = MaterialTheme.colorScheme.surfaceVariant
    val textColor = MaterialTheme.colorScheme.onSurface
    val placeholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.9f),
            value = text,
            singleLine = true,
            onValueChange = onInputText,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                }
            ),
            trailingIcon = {
                if(text.isNotBlank()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear text",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                onInputText("")
                            }
                    )
                }
            },
            colors = TextFieldColors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                disabledTextColor = textColor,
                errorTextColor = MaterialTheme.colorScheme.onError,
                focusedContainerColor = outlineColor,
                unfocusedContainerColor = backgroundColor,
                disabledContainerColor = backgroundColor,
                errorContainerColor = MaterialTheme.colorScheme.errorContainer,
                cursorColor = textColor,
                errorCursorColor = MaterialTheme.colorScheme.onError,
                textSelectionColors = TextSelectionColors(
                    handleColor = textColor,
                    backgroundColor = textColor.copy(alpha = 0.4f),
                ),
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = textColor,
                disabledIndicatorColor = textColor,
                errorIndicatorColor = MaterialTheme.colorScheme.onError,
                focusedLeadingIconColor = textColor,
                unfocusedLeadingIconColor = textColor,
                disabledLeadingIconColor = textColor,
                errorLeadingIconColor = MaterialTheme.colorScheme.onError,
                focusedTrailingIconColor = textColor,
                unfocusedTrailingIconColor = textColor,
                disabledTrailingIconColor = textColor,
                errorTrailingIconColor = MaterialTheme.colorScheme.onError,
                focusedLabelColor = textColor,
                unfocusedLabelColor = textColor,
                disabledLabelColor = textColor,
                errorLabelColor = MaterialTheme.colorScheme.onError,
                focusedPlaceholderColor = placeholderColor,
                unfocusedPlaceholderColor = placeholderColor,
                disabledPlaceholderColor = placeholderColor,
                errorPlaceholderColor = placeholderColor,
                focusedSupportingTextColor = textColor,
                unfocusedSupportingTextColor = textColor,
                disabledSupportingTextColor = textColor,
                errorSupportingTextColor = MaterialTheme.colorScheme.onError,
                focusedPrefixColor = textColor,
                unfocusedPrefixColor = textColor,
                disabledPrefixColor = textColor,
                errorPrefixColor = MaterialTheme.colorScheme.onError,
                focusedSuffixColor = textColor,
                unfocusedSuffixColor = textColor,
                disabledSuffixColor = textColor,
                errorSuffixColor = MaterialTheme.colorScheme.onError,
            ),
            placeholder = {
                if (text.isBlank()) {
                    Text(
                        text = placeHolder,
                        color = placeholderColor
                    )
                }
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(
            onClick = onSearch,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .weight(0.11f)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    var state by remember {
        mutableStateOf("")
    }
    SearchBar(
        modifier = Modifier.padding(16.dp),
        text = state,
        placeHolder = "Search...",
        onInputText = { 
            state = it
        },
        onSearch = {}
    )
}