package io.github.seoj17.canyongg.contract

object UrlContract {
    const val CDN_VERSION = "13.3.1"
    const val DATA_CENTER_URL = "https://ddragon.leagueoflegends.com"

    const val PROFILE_ICON_URL = "$DATA_CENTER_URL/cdn/$CDN_VERSION/img/profileicon/%d.png"
    const val SPELL_URL = "$DATA_CENTER_URL/cdn/$CDN_VERSION/img/spell/%s.png"
    const val ITEM_URL = "$DATA_CENTER_URL/cdn/$CDN_VERSION/img/item/%d.png"
    const val RUNE_URL = "$DATA_CENTER_URL/cdn/img/"
    const val CHAMPION_URL = "$DATA_CENTER_URL/cdn/$CDN_VERSION/img/champion/%s.png"
    const val ROTATION_CHAMPION_URL = "$DATA_CENTER_URL/cdn/img/champion/loading/%s_0.jpg"
}