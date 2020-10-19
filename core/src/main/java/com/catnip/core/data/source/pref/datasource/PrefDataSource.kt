package com.catnip.core.data.source.pref.datasource

import android.content.Context
import android.content.SharedPreferences

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class PrefDataSource(context: Context) {
    companion object {
        private const val PREFS_FILENAME = "pixagram-pref"
        private const val KEY_LAST_FETCH = "last_fetch_data"
    }

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var lastFetch: String
        get() = sharedPrefs.getString(KEY_LAST_FETCH, "") ?: ""
        set(value) = sharedPrefs.edit().putString(KEY_LAST_FETCH, value).apply()
}