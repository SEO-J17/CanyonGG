package io.github.seoj17.canyongg.ui.record

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord

class RecordListAdapter(
    private val itemClickListener: (String) -> Unit,
) : PagingDataAdapter<SummonerMatchRecord, RecordListViewHolder>(SummonerMatchRecord.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecordListViewHolder(parent)

    override fun onBindViewHolder(holder: RecordListViewHolder, position: Int) {
        val summonerInfo = getItem(position) ?: return
        with(holder) {
            bind(summonerInfo)
            itemView.setOnClickListener {
                itemClickListener(summonerInfo.matchId)
            }
        }
    }
}