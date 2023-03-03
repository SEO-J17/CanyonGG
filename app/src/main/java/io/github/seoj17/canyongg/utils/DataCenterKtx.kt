package io.github.seoj17.canyongg.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import io.github.seoj17.canyongg.contract.UrlContract

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