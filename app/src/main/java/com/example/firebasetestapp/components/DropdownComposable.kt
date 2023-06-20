package com.example.firebasetestapp.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownContextMenu(
    options: List<String>,
    modifier: Modifier,
    onActionClick: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        modifier = modifier,
        onExpandedChange = { isExpanded = !isExpanded }
    ) {
        Icon(
            modifier = Modifier.padding(8.dp, 0.dp),
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More"
        )

        ExposedDropdownMenu(
            modifier = Modifier.width(180.dp),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        onActionClick(selectionOption)
                    },
                    text = { Text(text = selectionOption) },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelector(
    @StringRes label: Int,
    options: List<String>,
    selection: String,
    modifier: Modifier,
    onNewValue: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        modifier = modifier,
        onExpandedChange = { isExpanded = !isExpanded }
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value = selection,
            onValueChange = {},
            label = { Text(stringResource(label)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded) },
            colors = dropdownColors()
        )

        ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        onNewValue(selectionOption)
                        isExpanded = false
                    },
                    text = { Text(text = selectionOption)}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun dropdownColors(): TextFieldColors {
    return ExposedDropdownMenuDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.primary
    )
}
