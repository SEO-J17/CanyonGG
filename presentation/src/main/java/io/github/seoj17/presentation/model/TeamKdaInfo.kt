package io.github.seoj17.presentation.model

data class TeamKdaInfo(
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val win: Boolean,
    val playedTime: Int,
) {
    companion object {
        fun toKdaInfo(matchList: List<SummonerMatchRecord>, isWin: Boolean): TeamKdaInfo {
            return TeamKdaInfo(
                kills = matchList.sumOf { it.kill },
                deaths = matchList.sumOf { it.death },
                assists = matchList.sumOf { it.assist },
                win = isWin,
                playedTime = matchList.firstOrNull()?.playedTime ?: 0,
            )
        }
    }
}
