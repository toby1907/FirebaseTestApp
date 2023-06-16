package com.example.firebasetestapp.service

import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<com.example.firebasetestapp.model.User>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String,password: String)
    suspend fun deleteAccount()
    suspend fun signOut()

}