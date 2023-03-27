package io.github.seoj17.presentaion.ui.adapter

import androidx.annotation.StringRes
import io.github.seoj17.presentaion.R

sealed class MostKillState(
    @StringRes val killName: Int,
) {
    object ZERO : MostKillState(
        killName = R.string.zero_kill,
    )

    object SINGLE : MostKillState(
        killName = R.string.sole_kill,
    )

    object DOUBLE : MostKillState(
        killName = R.string.double_kill,
    )

    object TRIPLE : MostKillState(
        killName = R.string.triple_kill,
    )

    object QUADRA : MostKillState(
        killName = R.string.quadra_kill,
    )

    object PENTA : MostKillState(
        killName = R.string.penta_kill,
    )

    companion object {
        operator fun invoke(kill: Int): Int {
            return values().getOrNull(kill)?.killName ?: ZERO.killName
        }

        private fun values(): List<MostKillState> {
            return listOf(ZERO, SINGLE, DOUBLE, TRIPLE, QUADRA, PENTA)
        }
    }
}
