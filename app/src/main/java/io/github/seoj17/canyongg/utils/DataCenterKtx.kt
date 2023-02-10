package io.github.seoj17.canyongg.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

private const val CDN_VERSION = "13.3.1"
private const val CENTER_URL = "https://ddragon.leagueoflegends.com"

fun ImageView.setSpell(spell: String) {
    val url = "$CENTER_URL/cdn/$CDN_VERSION/img/spell/"
    Glide
        .with(this.context)
        .load("${url}${spell}.png")
        .into(this)
}

fun ImageView.setItems(item: Int) {
    val url = "$CENTER_URL/cdn/$CDN_VERSION/img/item/"
    Glide
        .with(this.context)
        .load("${url}${item}.png")
        .into(this)
}

fun ImageView.setRunes(rune: String) {
    val url = "$CENTER_URL/cdn/img/"
    Glide
        .with(this.context)
        .load("${url}${rune}")
        .into(this)
}

fun ImageView.setChampion(champ: String) {
    val imgUrl = "$CENTER_URL/cdn/$CDN_VERSION/img/champion/"
    Glide
        .with(this.context)
        .load("${imgUrl}${champ}.png")
        .into(this)
}