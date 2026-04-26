package com.whyelab.careersystemandroid.data.local

import com.whyelab.careersystemandroid.data.local.PrefKeys.LOCAL_HTTP_SCHEME
import com.whyelab.careersystemandroid.data.local.PrefKeys.PREF_NAME
import com.whyelab.careersystemandroid.data.local.PrefKeys.BASE_URL

import android.content.Context
import androidx.core.content.edit

class ConfigManager(context: Context) {

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveBaseUrl(ip: String, port: String) {
        val url = "$LOCAL_HTTP_SCHEME://$ip:$port/"
        prefs.edit { putString(BASE_URL, url) }
    }

    fun getBaseUrl(): String? {
        return prefs.getString(BASE_URL, null)
    }

    fun hasConfig(): Boolean {
        return getBaseUrl() != null
    }
}