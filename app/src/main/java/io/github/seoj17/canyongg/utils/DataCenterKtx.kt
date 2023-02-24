package io.github.seoj17.canyongg.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import io.github.seoj17.canyongg.contract.UrlContract

fun ImageView.setSpell(spell: String) {
    Glide
        .with(this.context)
        .load(String.format(UrlContract.SPELL_URL, spell))
        .into(this)
}

fun ImageView.setItems(item: Int) {
    Glide
        .with(this.context)
        .load(String.format(UrlContract.ITEM_URL, item))
        .into(this)
}

fun ImageView.setRunes(rune: String) {
    Glide
        .with(this.context)
        .load("${UrlContract.RUNE_URL}${rune}")
        .into(this)
}

fun ImageView.setChampion(champ: String) {
    Glide
        .with(this.context)
        .load(String.format(UrlContract.CHAMPION_URL, champ))
        .into(this)
}

fun ImageView.setRotationChampion(champ: String) {
    Glide
        .with(this.context)
        .load(String.format(UrlContract.ROTATION_CHAMPION_URL, champ))
        .into(this)
}