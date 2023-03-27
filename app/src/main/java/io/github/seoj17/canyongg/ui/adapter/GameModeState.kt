package io.github.seoj17.canyongg.ui.adapter

import androidx.annotation.StringRes
import io.github.seoj17.canyongg.R

enum class GameModeState(
    val gameMode: String,
    @StringRes val modeName: Int,
) {
    CLASSIC(
        gameMode = "CLASSIC",
        modeName = R.string.classic_mode,
    ),
    ARAM(
        gameMode = "ARAM",
        modeName = R.string.aram_mode,
    ),
    TUTORIAL(
        gameMode = "TUTORIAL",
        modeName = R.string.tutorial_mode,
    ),
    URF(
        gameMode = "URF",
        modeName = R.string.urf_mode,
    ),
    ULTBOOK(
        gameMode = "ULTBOOK",
        modeName = R.string.ultimate_mode,
    ),
    ;

    companion object {
        operator fun invoke(mode: String): Int {
            return values()
                .first {
                    it.gameMode == mode
                }
                .modeName
        }
    }
}
