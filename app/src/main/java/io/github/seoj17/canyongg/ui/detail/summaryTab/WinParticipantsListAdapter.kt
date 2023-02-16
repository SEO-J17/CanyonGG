package io.github.seoj17.canyongg.ui.detail.summaryTab

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.seoj17.canyongg.ui.model.ParticipantsMatches

class WinParticipantsListAdapter(
    private val itemClickListener: (String, String) -> Unit,
) : ListAdapter<ParticipantsMatches, WinParticipantsListViewHolder>(ParticipantsMatches.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WinParticipantsListViewHolder(parent)

    override fun onBindViewHolder(holder: WinParticipantsListViewHolder, position: Int) {
        val data = getItem(position) ?: return
        with(holder) {
            bind(data)
            itemView.setOnClickListener {
                itemClickListener(data.summonerName, data.puuid)
            }
        }
    }
}