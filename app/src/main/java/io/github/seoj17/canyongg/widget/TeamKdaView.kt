package io.github.seoj17.canyongg.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.ViewTeamKdaBinding
import io.github.seoj17.canyongg.ui.adapter.setMatchPlayedTime
import io.github.seoj17.canyongg.ui.model.TeamKdaInfo

@BindingMethods(
    value = [
        BindingMethod(
            type = TeamKdaView::class,
            attribute = "teamKdaInfo",
            method = "setTeamKdaInfo"
        ),
    ]
)

class TeamKdaView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private val binding = ViewTeamKdaBinding.inflate(LayoutInflater.from(context), this, true)

    var teamKdaInfo: TeamKdaInfo? = null
        set(value) {
            value?.let {
                teamWin = it.win
                teamKill = it.kills
                teamDeath = it.deaths
                teamAssists = it.assists
                playedTime = it.playedTime
            }
            field = value
        }


    var teamWin: Boolean? = null
        set(value) {
            binding
                .winResult
                .setText(
                    if (value == true) {
                        R.string.victory_label
                    } else {
                        R.string.lose_label
                    }
                )
            field = value
        }

    var teamKill: Int = 0
        set(value) {
            binding.teamKills.text = context.getString(R.string.summoner_kill, value)
            field = value
        }

    var teamDeath: Int = 0
        set(value) {
            binding.teamDeaths.text = context.getString(R.string.summoner_death, value)
            field = value
        }

    var teamAssists: Int = 0
        set(value) {
            binding.teamAssists.text = context.getString(R.string.summoner_assist, value)
            field = value
        }

    var playedTime: Int = 0
        set(value) {
            binding.matchDate.setMatchPlayedTime(value)
            field = value
        }
}