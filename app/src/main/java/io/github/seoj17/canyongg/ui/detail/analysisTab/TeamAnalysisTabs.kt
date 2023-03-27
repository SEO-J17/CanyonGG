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
    val newInstance: () -> Fragment,
) {
    KILLS(
        title = R.string.sub_pager_kill,
        newInstance = TeamKillsFragment::newInstance,
    ),

    DEALT(
        title = R.string.sub_pager_dealt,
        newInstance = TeamDealtFragment::newInstance,
    ),

    DAMAGED(
        title = R.string.sub_pager_damaged,
        newInstance = TeamDamagedFragment::newInstance,
    ),

    SPENT_GOLD(
        title = R.string.sub_pager_spent_gold,
        newInstance = TeamGoldFragment::newInstance,
    ),

    MINIONS(
        title = R.string.sub_pager_minions,
        newInstance = TeamMinionFragment::newInstance,
    ),

    VISION_SCORE(
        title = R.string.sub_pager_vision_score,
        newInstance = TeamVisionScoreFragment::newInstance,
    ),
    ;

    companion object {
        operator fun invoke(position: Int) = values().get(position)
    }
}
