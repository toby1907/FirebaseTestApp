package com.example.firebasetestapp.service

interface ConfigutationService{
    suspend fun fetchConfiguration(): Boolean
    val isShowTaskEditButtonConfig: Boolean
}