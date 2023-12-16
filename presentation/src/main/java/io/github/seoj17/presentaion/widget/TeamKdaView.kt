package io.github.seoj17.presentaion.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.ViewTeamKdaBinding
import io.github.seoj17.presentaion.model.TeamKdaInfo
import io.github.seoj17.presentaion.ui.setMatchPlayedTime

@BindingMethods(
    value = [
        BindingMethod(
            type = TeamKdaView::class,
            attribute = "teamKdaInfo",
            method = "setTeamKdaInfo",
        ),
    ],
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
            with(binding) {
                val (textLabelResId, textColorResId, textStrokeDrawable) =
                    if (value == true) {
                        Triple(
                            R.string.victory_label,
                            R.color.winner_loading_text_color,
                            R.drawable.win_text_stroke,
                        )
                    } else {
                        Triple(
                            R.string.lose_label,
                            R.color.loser_loading_text_color,
                            R.drawable.lose_text_stroke,
                        )
                    }

                winResult.setText(textLabelResId)
                matchDate.setTextColor(context.getColor(textColorResId))
                winResult.setTextColor(context.getColor(textColorResId))
                infoLayout.setBackgroundResource(textStrokeDrawable)
            }
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
