package io.github.sahalnazar.darkmode

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sahalnazar.darkmode.data.sharedpref.Preference
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preference: Preference
) : ViewModel() {

    var selectedTheme
        get() = preference.appTheme
        set(value) {
            preference.appTheme = value
        }


}