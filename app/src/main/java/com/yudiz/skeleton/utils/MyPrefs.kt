package com.yudiz.skeleton.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MyPrefs @Inject constructor(@ApplicationContext context: Context) {

    private var masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    val preferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "SkeletonSharedPreferences",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    operator fun set(key: String?, value: Any?) {

        when (value) {
            is String -> {
                preferences.edit().putString(key, value).apply()
            }
            is Int -> {
                preferences.edit().putInt(key, value).apply()
            }
            is Float -> {
                preferences.edit().putFloat(key, value).apply()
            }
            is Boolean -> {
                preferences.edit().putBoolean(key, value).apply()
            }
        }
    }

    inline operator fun <reified T> get(key: String, defaultValue: T?): T {
        return when (T::class) {
            String::class -> {
                preferences.getString(key, defaultValue as String) as T
            }
            Int::class -> {
                preferences.getInt(key, defaultValue as Int) as T
            }
            Float::class -> {
                preferences.getFloat(key, defaultValue as Float) as T
            }
            Boolean::class -> {
                preferences.getBoolean(key, defaultValue as Boolean) as T
            }
            else -> {
                throw UnsupportedOperationException("Not yet implemented")
            }
        }
    }
}