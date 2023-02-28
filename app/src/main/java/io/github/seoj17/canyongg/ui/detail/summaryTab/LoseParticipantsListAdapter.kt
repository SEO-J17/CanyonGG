package io.github.seoj17.canyongg.ui.detail.summaryTab

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord

class LoseParticipantsListAdapter(
    private val itemClickListener: (String, String) -> Unit,
) : ListAdapter<SummonerMatchRecord, LoseParticipantsListViewHolder>(SummonerMatchRecord.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LoseParticipantsListViewHolder(parent)

    override fun onBindViewHolder(holder: LoseParticipantsListViewHolder, position: Int) {
        val data = getItem(position) ?: return
        with(holder) {
            bind(data, currentList)
            itemView.setOnClickListener {
                itemClickListener(data.summonerName, data.puuid)
            }
        }
    }
}