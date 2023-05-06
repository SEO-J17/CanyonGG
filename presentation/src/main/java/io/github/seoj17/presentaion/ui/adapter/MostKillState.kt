package io.github.seoj17.presentaion.ui.adapter

import androidx.annotation.StringRes
import io.github.seoj17.presentaion.R

sealed class MostKillState(
    @StringRes val killName: Int,
    val kill: Int,
) {
    object ZERO : MostKillState(
        killName = R.string.zero_kill,
        kill = 0,
    )

    object SINGLE : MostKillState(
        killName = R.string.sole_kill,
        kill = 1,
    )

    object DOUBLE : MostKillState(
        killName = R.string.double_kill,
        kill = 2,
    )

    object TRIPLE : MostKillState(
        killName = R.string.triple_kill,
        kill = 3,
    )

    object QUADRA : MostKillState(
        killName = R.string.quadra_kill,
        kill = 4,
    )

    object PENTA : MostKillState(
        killName = R.string.penta_kill,
        kill = 5,
    )

    companion object {
        operator fun invoke(kill: Int): MostKillState {
            return when (kill) {
                ZERO.kill -> ZERO
                SINGLE.kill -> SINGLE
                DOUBLE.kill -> DOUBLE
                TRIPLE.kill -> TRIPLE
                QUADRA.kill -> QUADRA
                else -> PENTA
            }
        }
    }
}
