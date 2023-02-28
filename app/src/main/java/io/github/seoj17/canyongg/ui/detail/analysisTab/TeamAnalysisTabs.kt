package io.github.seoj17.canyongg.ui.detail.analysisTab

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.damaged.TeamDamagedFragment
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.dealt.TeamDealtFragment
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.gold.TeamGoldFragment
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.kill.TeamKillsFragment
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.minion.TeamMinionFragment
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.vision.TeamVisionScoreFragment

enum class TeamAnalysisTabs(
    @StringRes val title: Int,
    val newInstance: Fragment,
    val position: Int,
) {
    KILLS(
        title = R.string.sub_pager_kill,
        newInstance = TeamKillsFragment.newInstance(),
        position = 0,
    ),

    DEALT(
        title = R.string.sub_pager_dealt,
        newInstance = TeamDealtFragment.newInstance(),
        position = 1,
    ),

    DAMAGED(
        title = R.string.sub_pager_damaged,
        newInstance = TeamDamagedFragment.newInstance(),
        position = 2,
    ),

    SPENT_GOLD(
        title = R.string.sub_pager_spent_gold,
        newInstance = TeamGoldFragment.newInstance(),
        position = 3,
    ),

    MINIONS(
        title = R.string.sub_pager_minions,
        newInstance = TeamMinionFragment.newInstance(),
        position = 4,
    ),

    VISION_SCORE(
        title = R.string.sub_pager_vision_score,
        newInstance = TeamVisionScoreFragment.newInstance(),
        position = 5,
    ),
}