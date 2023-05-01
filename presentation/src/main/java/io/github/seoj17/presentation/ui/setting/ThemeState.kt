package io.github.seoj17.presentation.ui.setting

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
        operator fun invoke(index: Int?): ThemeState {
            return values().getOrNull(index ?: 0) ?: SYSTEM
        }
    }
}
