package com.example.firebasetestapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun PermissionDialog(onRequestPermission: () -> Unit) {
    var showWarningDialog by remember {
        mutableStateOf(true)
    }
    if (showWarningDialog){
        AlertDialog(modifier =Modifier.alertDialog()) {

        }
    }
}