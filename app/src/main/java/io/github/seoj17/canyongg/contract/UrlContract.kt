package io.github.seoj17.canyongg.contract

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

object UrlContract {
    private const val DATA_CENTER_URL = "https://ddragon.leagueoflegends.com"
    private val cdnVersion = Firebase.remoteConfig.getString(RemoteConfigContract.REMOTE_CONFIG_KEY)

    const val RUNE_URL = "$DATA_CENTER_URL/cdn/img/"

    fun profileIconUrl(id: Int): String {
        return "$DATA_CENTER_URL/cdn/$cdnVersion/img/profileicon/$id.png"
    }

    fun spellUrl(spell: String): String {
        return "$DATA_CENTER_URL/cdn/$cdnVersion/img/spell/$spell.png"
    }

    fun itemUrl(item: Int): String {
        return "$DATA_CENTER_URL/cdn/$cdnVersion/img/item/$item.png"
    }

    fun championUrl(name: String): String {
        return "$DATA_CENTER_URL/cdn/$cdnVersion/img/champion/$name.png"
    }

    fun rotationChampionUrl(name: String): String {
        return "$DATA_CENTER_URL/cdn/img/champion/loading/${name}_0.jpg"
    }

}