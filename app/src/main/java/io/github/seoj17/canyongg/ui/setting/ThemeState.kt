package io.github.seoj17.canyongg.ui.setting

import androidx.appcompat.app.AppCompatDelegate

enum class ThemeState(
    val mode: Int,
) {
    SYSTEM(
        mode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
    ),
    LIGHT(
        mode = AppCompatDelegate.MODE_NIGHT_NO,
    ),
    DARK(
        mode = AppCompatDelegate.MODE_NIGHT_YES,
    ),
    ;

    companion object {
        fun getMode(mode: Int): Int {
            return values().getOrNull(mode)?.mode ?: SYSTEM.mode
        }

        fun getKey(): String {
            return "theme_mode"
        }
    }
}
