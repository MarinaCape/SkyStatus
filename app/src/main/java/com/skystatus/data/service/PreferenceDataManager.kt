package com.skystatus.data.service

import android.content.SharedPreferences
import timber.log.Timber

abstract class PreferenceDataManager constructor(
    private val preferences: SharedPreferences,
    private val cacheInMemory: Boolean = false
) {

    private val cache = HashMap<PreferenceKey, Any?>()

    protected open fun cacheEnabled(key: PreferenceKey): Boolean = cacheInMemory

    protected open fun setString(key: PreferenceKey, value: String?) {
        if (cacheEnabled(key)) {
            logSaveCachePreference(key, value)
            cache[key] = value
        }
        logSavePreference(key, value)
        preferences.edit().putString(key.keyString(), value).apply()
    }

    protected open fun setBoolean(key: PreferenceKey, value: Boolean?) {
        if (cacheEnabled(key)) {
            logSaveCachePreference(key, value)
            cache[key] = value
        }
        logSavePreference(key, value)
        preferences.edit().putBoolean(key.keyString(), value!!).apply()
    }

    protected open fun setInt(key: PreferenceKey, value: Int) {
        if (cacheEnabled(key)) {
            logSaveCachePreference(key, value)
            cache[key] = value
        }
        logSavePreference(key, value)
        preferences.edit().putInt(key.keyString(), value).apply()
    }

    protected open fun setFloat(key: PreferenceKey, value: Float) {
        if (cacheEnabled(key)) {
            cache[key] = value
            logSaveCachePreference(key, value)
        }
        logSavePreference(key, value)
        preferences.edit().putFloat(key.keyString(), value).apply()
    }

    private fun logSaveCachePreference(key: PreferenceKey, value: Any?) {
        Timber.d("${this.javaClass.simpleName} - Setting cache preference: $key -> $value")
    }

    private fun logSavePreference(key: PreferenceKey, value: Any?) {
        Timber.d("${this.javaClass.simpleName} - Setting preference: $key -> $value")
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    protected open fun getString(key: PreferenceKey, defValue: String = ""): String {
        return getStringNullable(key, defValue).orEmpty()
    }

    protected open fun getStringNullable(key: PreferenceKey, defValue: String? = null): String? {
        val result: String?
        if (cacheEnabled(key) && cache.containsKey(key)) {
            result = cache[key] as String? ?: defValue
            logGetCachePreference(key, result)
        } else {
            result = preferences.getString(key.keyString(), defValue)
            logGetPreference(key, result)
        }
        return result
    }

    protected open fun getBoolean(key: PreferenceKey, defValue: Boolean = false): Boolean {
        val result: Boolean?
        if (cacheEnabled(key) && cache.containsKey(key)) {
            result = cache[key] as Boolean? ?: defValue
            logGetCachePreference(key, result)
        } else {
            result = preferences.getBoolean(key.keyString(), defValue)
            logGetPreference(key, result)
        }
        return result
    }

    protected open fun getInt(key: PreferenceKey, defValue: Int): Int {
        val result: Int?
        if (cacheEnabled(key) && cache.containsKey(key)) {
            result = cache[key] as Int? ?: defValue
            logGetCachePreference(key, result)
        } else {
            result = preferences.getInt(key.keyString(), defValue)
            logGetPreference(key, result)
        }
        return result
    }

    protected open fun getFloat(key: PreferenceKey, defValue: Float): Float {
        val result: Float?
        if (cacheEnabled(key) && cache.containsKey(key)) {
            result = cache[key] as Float? ?: defValue
            logGetCachePreference(key, result)
        } else {
            result = preferences.getFloat(key.keyString(), defValue)
            logGetPreference(key, result)
        }
        return result
    }

    private fun logGetCachePreference(key: PreferenceKey, value: Any?) {
        Timber.d("${this.javaClass.simpleName} - Getting persisted cache preference: $key -> $value")
    }

    private fun logGetPreference(key: PreferenceKey, value: Any?) {
        Timber.d("${this.javaClass.simpleName} - Getting persisted preference: $key -> $value")
    }

    protected open fun clear() {
        cache.clear()
        preferences.edit().clear().apply()
    }

    protected open fun clearKey(key: PreferenceKey) {
        cache.remove(key)
        preferences.edit().remove(key.keyString()).apply()
    }

    interface PreferenceKey {
        fun keyString(): String
    }
}