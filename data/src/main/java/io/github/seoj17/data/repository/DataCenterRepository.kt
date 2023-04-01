package io.github.seoj17.data.repository

interface DataCenterRepository {
    suspend fun getSpell(key: Int): String
}
