package io.github.seoj17.canyongg.ui.adapter

import androidx.annotation.StringRes
import io.github.seoj17.canyongg.R

enum class MostKillState(
    @StringRes val killName: Int,
) {
    ZERO(
        killName = R.string.zero_kill
    ),
    SINGLE(
        killName = R.string.sole_kill
    ),
    DOUBLE(
        killName = R.string.double_kill
    ),
    TRIPLE(
        killName = R.string.triple_kill
    ),
    QUADRA(
        killName = R.string.quadra_kill
    ),
    PENTA(
        killName = R.string.penta_kill
    );

    companion object {
        fun getMostKill(kill: Int): Int {
            return MostKillState
                .values()
                .getOrNull(kill)
                ?.killName
                ?: ZERO.killName
        }
    }
}
