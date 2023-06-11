package com.skystatus.presentation.core

import android.app.Application
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class SkyStatusApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
            private set
    }

}