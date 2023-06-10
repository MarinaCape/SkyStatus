package com.skystatus.data.service

import android.content.SharedPreferences
import javax.inject.Inject

class SettingsService @Inject constructor(
    preferences: SharedPreferences
) : PreferenceDataManager(preferences) {

    var cityFavourite: String?
        get() = getString(Key.CITY_FAVOURITE)
        set(value) = setString(Key.CITY_FAVOURITE, value)

    private enum class Key : PreferenceKey {
        CITY_FAVOURITE;

        override fun keyString() = name
    }
}