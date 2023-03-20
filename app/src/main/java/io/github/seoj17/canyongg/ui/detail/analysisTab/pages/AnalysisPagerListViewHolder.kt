package io.github.seoj17.canyongg.ui.detail.analysisTab.pages

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord

abstract class AnalysisPagerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(dataSet: SummonerMatchRecord, wholeData: List<SummonerMatchRecord>)

    interface CreateViewHolder {
        operator fun invoke(parent: ViewGroup): AnalysisPagerListViewHolder
    }
}
