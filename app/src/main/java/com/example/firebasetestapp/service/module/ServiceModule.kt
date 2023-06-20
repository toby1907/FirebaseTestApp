package com.example.firebasetestapp.service.module

import com.example.firebasetestapp.service.AccountService
import com.example.firebasetestapp.service.ConfigurationService
import com.example.firebasetestapp.service.LogService
import com.example.firebasetestapp.service.StorageService
import com.example.firebasetestapp.service.impl.AccountServiceImpl
import com.example.firebasetestapp.service.impl.ConfigurationServiceImpl
import com.example.firebasetestapp.service.impl.LogServiceImpl
import com.example.firebasetestapp.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule{
    @Binds abstract  fun provideAccountService(impl: AccountServiceImpl): AccountService
    @Binds abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

    @Binds
    abstract fun provideConfigurationService(impl: ConfigurationServiceImpl): ConfigurationService

}
