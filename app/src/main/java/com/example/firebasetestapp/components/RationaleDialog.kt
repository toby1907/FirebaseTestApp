package com.example.firebasetestapp.components


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.firebasetestapp.ui.theme.BrightOrange
import com.example.firebasetestapp.R.string as AppText

@Composable
fun PermissionDialog(onRequestPermission: () -> Unit) {
    var showWarningDialog by remember {
        mutableStateOf(true)
    }
    if (showWarningDialog) {
        AlertDialog(modifier = Modifier.alertDialog(),
            title = { Text(text = stringResource(id = AppText.notification_permission_title)) },
            text = { Text(text = stringResource(id = AppText.notification_permission_description)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onRequestPermission()
                        showWarningDialog = false
                    },
                    modifier = Modifier.textButton(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrightOrange, contentColor = Color.White
                    ),
                    content = {
                        Text(text = stringResource(id = AppText.request_notification_permission))
                    },
                )
            },
            onDismissRequest = {})

    }

}

@Composable
fun RationaleDialog() {
    var showWarningDialog by remember { mutableStateOf(true) }

    if (showWarningDialog) {
        AlertDialog(modifier = Modifier.alertDialog(),
            title = { Text(text = stringResource(id = AppText.notification_permission_title)) },
            text = { Text(text = stringResource(id = AppText.notification_permission_settings)) },
            confirmButton = {
                TextButton(
                    onClick = { showWarningDialog = false },
                    modifier = Modifier.textButton(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrightOrange,
                        contentColor = Color.White
                    )
                )
                {
                    Text(text = stringResource(id = AppText.ok))
                }
            },
            onDismissRequest = { showWarningDialog = false })
    }
}