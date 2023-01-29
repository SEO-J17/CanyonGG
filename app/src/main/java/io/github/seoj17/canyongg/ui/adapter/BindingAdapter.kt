package io.github.seoj17.canyongg.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind:userIcon")
fun ImageView.setThumbnail(imgId: Int) {
    val imgUrl = "https://ddragon.leagueoflegends.com/cdn/13.1.1/img/profileicon/"
    Glide.with(this.context)
        .load("${imgUrl}${imgId}.png").into(this)
}


@BindingAdapter("bind:champImg", "bind:champIndex")
fun ImageView.setChampion(
    champList: List<String>?,
    index: Int,
) {
    val imgUrl = "https://ddragon.leagueoflegends.com/cdn/13.1.1/img/champion/"
    champList?.let {
        val champ = champList[index]
        Glide.with(this.context)
            .load("${imgUrl}${champ}.png").into(this)
    }
}

@BindingAdapter("bind:champList", "bind:champWinMap", "bind:champIndex")
fun TextView.setWinScore(
    champList: List<String>?,
    champMap: Map<String, Int>?,
    index: Int,
) {
    champList?.let {
        val champ = champList[index]
        this.text = "${champMap?.get(champ)}%"
    }
}

@BindingAdapter("bind:champList", "bind:champKdaMap", "bind:champIndex")
fun TextView.setChampKda(
    champList: List<String>?,
    champMap: Map<String, Double>?,
    index: Int,
) {
    champList?.let {
        val champ = champList[index]
        this.text = "${String.format("%.2f", champMap?.get(champ))}:1"
    }
}