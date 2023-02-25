package io.github.seoj17.canyongg.ui.setting

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import io.github.seoj17.canyongg.R

class SettingPreference
    : PreferenceFragmentCompat(),
    Preference.OnPreferenceChangeListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preference, rootKey)
        val preference: ListPreference? = findPreference(getString(R.string.setting_theme_list_key))
        preference?.onPreferenceChangeListener = this
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
        if (preference.key == getString(R.string.setting_theme_list_key)) {
            when (newValue as? String) {
                getString(R.string.setting_light_theme_value) -> {
                    requireActivity().setTheme(R.style.Theme_Light)
                }
                getString(R.string.setting_dark_theme_value) -> {
                    requireActivity().setTheme(R.style.Theme_Dark)
                }
            }
            requireActivity().recreate()
        }
        return true
    }
}


// val preference: ListPreference? = findPreference(getString(R.string.setting_theme_list_key))
//        preference?.onPreferenceChangeListener =
//            Preference.OnPreferenceChangeListener { preference, newValue ->
//                when (newValue.toString()) {
//                    getString(R.string.setting_light_theme_value) -> {
//                        updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
//                    }
//                    getString(R.string.setting_dark_theme_value) -> {
//                        updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
//                    }
//                    else -> throw Exception()
//                }
//            }
//    }
//
//    private fun updateTheme(mode: Int): Boolean {
//        AppCompatDelegate.setDefaultNightMode(mode)
//        return true
//    }