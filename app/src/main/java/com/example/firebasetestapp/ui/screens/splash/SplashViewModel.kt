package com.example.firebasetestapp.ui.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.example.firebasetestapp.FirebasetestAppViewModel
import com.example.firebasetestapp.service.AccountService
import com.example.firebasetestapp.service.ConfigurationService
import com.example.firebasetestapp.service.LogService
import com.example.firebasetestapp.ui.screens.SPLASH_SCREEN
import com.example.firebasetestapp.ui.screens.TASKS_SCREEN
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    configurationService: ConfigurationService,
    private val accountService: AccountService,
    logService: LogService
) : FirebasetestAppViewModel(logService) {
    val showError = mutableStateOf(false)

    init {
        launchCatching { configurationService.fetchConfiguration() }
    }

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {

        showError.value = false
        if (accountService.hasUser) openAndPopUp(TASKS_SCREEN, SPLASH_SCREEN)
        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        launchCatching(snackbar = false) {
            try {
                accountService.createAnonymousAccount()
            } catch (ex: FirebaseAuthException) {
                showError.value = true
                throw ex
            }
            openAndPopUp(TASKS_SCREEN, SPLASH_SCREEN)
        }
    }
}