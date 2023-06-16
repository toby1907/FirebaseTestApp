package com.example.firebasetestapp.service.module

import com.example.firebasetestapp.service.AccountService
import com.example.firebasetestapp.service.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule{
    @Binds abstract  fun provideAccountService(impl: AccountServiceImpl): AccountService


}
