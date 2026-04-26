package com.whyelab.careersystemandroid.data.local

import android.content.Context

class ConfigManager(context: Context) {

    private val prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE)

    fun saveBaseUrl(ip: String, port: String) {
        val url = "http://$ip:$port/"
        prefs.edit().putString("base_url", url).apply()
    }

    fun getBaseUrl(): String? {
        return prefs.getString("base_url", null)
    }

    fun hasConfig(): Boolean {
        return getBaseUrl() != null
    }
}