package com.whyelab.careersystemandroid.util

fun isValidIp(ip: String): Boolean {
    val parts = ip.split(".")
    if (parts.size != 4) return false

    return parts.all {
        it.toIntOrNull()?.let { n -> n in 0..255 } ?: false
    }
}