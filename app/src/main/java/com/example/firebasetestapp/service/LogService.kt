package com.example.firebasetestapp.service


interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}