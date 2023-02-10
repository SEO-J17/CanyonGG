package io.github.seoj17.canyongg.data.repository


interface DataCenterRepository {
    suspend fun getSpell(key: Int): String
    suspend fun getMainPerk(mainId: Int, detailId: Int): String
    suspend fun getSubPerk(id: Int): String
    suspend fun getChamp(champId: Int): String
}