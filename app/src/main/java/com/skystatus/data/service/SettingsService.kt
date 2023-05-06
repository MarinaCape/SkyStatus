package com.skystatus.data.service

import android.content.SharedPreferences
import javax.inject.Inject

class SettingsService @Inject constructor(
    preferences: SharedPreferences
) : PreferenceDataManager(preferences) {

    var token: String?
        get() = getString(Key.TOKEN)
        set(value) = setString(Key.TOKEN, value)

    private enum class Key : PreferenceKey {
        TOKEN;

        override fun keyString() = name
    }
}