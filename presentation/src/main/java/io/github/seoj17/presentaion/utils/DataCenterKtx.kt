package io.github.seoj17.presentaion.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import io.github.seoj17.presentaion.contract.UrlContract

fun ImageView.setChampion(champ: String) {
    Glide
        .with(this.context)
        .load(UrlContract.championUrl(champ))
        .into(this)
}

fun ImageView.setRotationChampion(champ: String) {
    Glide
        .with(this.context)
        .load(UrlContract.splashChampionUrl(champ))
        .into(this)
}
