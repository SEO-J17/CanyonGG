package io.github.seoj17.canyongg.data.repository

interface DataCenterRepository {
    suspend fun getSpell(key: Int): String
}
