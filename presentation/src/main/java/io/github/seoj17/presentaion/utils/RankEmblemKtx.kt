package io.github.seoj17.presentaion.utils

import androidx.annotation.DrawableRes
import io.github.seoj17.presentaion.R

@DrawableRes
fun String.rankEmblemResId(): Int? {
    val emblemMap = mapOf(
        "CHALLENGER" to R.drawable.emblem_challenger,
        "GRANDMASTER" to R.drawable.emblem_grandmaster,
        "MASTER" to R.drawable.emblem_master,
        "DIAMOND" to R.drawable.emblem_diamond,
        "PLATINUM" to R.drawable.emblem_platinum,
        "GOLD" to R.drawable.emblem_gold,
        "SILVER" to R.drawable.emblem_silver,
        "BRONZE" to R.drawable.emblem_bronze,
        "IRON" to R.drawable.emblem_iron,
    )
    return emblemMap.getOrDefault(this.split(" ")[0], null)
}
