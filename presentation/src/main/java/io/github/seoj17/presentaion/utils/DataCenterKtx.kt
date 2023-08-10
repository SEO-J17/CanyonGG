package io.github.seoj17.presentaion.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.contract.UrlContract

fun ImageView.setChampion(champ: String) {
    Glide
        .with(this.context)
        .load(UrlContract.championUrl(champ))
        .transform(CenterCrop(), RoundedCorners(16))
        .placeholder(R.drawable.default_img)
        .into(this)
}

fun ImageView.setRotationChampion(champ: String) {
    Glide
        .with(this.context)
        .load(UrlContract.splashChampionUrl(champ))
        .transform(CenterCrop(), RoundedCorners(16))
        .placeholder(R.drawable.default_img)
        .into(this)
}
