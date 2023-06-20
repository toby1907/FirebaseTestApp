package com.example.firebasetestapp.service

interface ConfigurationService{
    suspend fun fetchConfiguration(): Boolean
    val isShowTaskEditButtonConfig: Boolean
}