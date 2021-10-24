package io.github.sahalnazar.darkmode.data.sharedpref

import android.content.SharedPreferences
import androidx.core.content.edit
import io.github.sahalnazar.darkmode.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Preference @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val KEY_THEME_PREF = "KEY_THEME_PREF"
    }

    var appTheme: String?
        get() = sharedPreferences.getString(KEY_THEME_PREF, Constants.DEVICE_THEME)
        set(value) = sharedPreferences.edit { putString(KEY_THEME_PREF, value) }

}