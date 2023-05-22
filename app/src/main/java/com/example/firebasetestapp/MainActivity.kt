package com.example.firebasetestapp

import MakeItSoTheme
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebasetestapp.ui.theme.FirebaseTestAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakeItSoApp()

        }
    }
}

@Composable
fun MakeItSoApp() {
   MakeItSoTheme {
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
           RequestNotificationPermissionDialog()

       }


   }
}
@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RequestNotificationPermissionDialog(){
    val permissionState = rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)
    if (!permissionState.status.isGranted){
        if (permissionState.status.shouldShowRationale) RationalDialog()
        else PermissionDialog{ permissionState.launchPermissionRequest()}
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirebaseTestAppTheme {
        MakeItSoApp()
    }
}