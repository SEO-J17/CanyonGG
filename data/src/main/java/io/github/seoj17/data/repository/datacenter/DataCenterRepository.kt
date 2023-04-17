package io.github.seoj17.data.repository.datacenter

interface DataCenterRepository {
    suspend fun getSpell(key: Int): String
}
