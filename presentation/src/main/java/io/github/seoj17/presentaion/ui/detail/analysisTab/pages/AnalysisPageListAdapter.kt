package io.github.seoj17.presentaion.ui.detail.analysisTab.pages

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.seoj17.presentaion.model.SummonerMatchRecord

class AnalysisPageListAdapter(
    private val pageType: AnalysisPagerTabs,
) : ListAdapter<SummonerMatchRecord, AnalysisPagerListViewHolder>(SummonerMatchRecord.diffUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AnalysisPagerListViewHolder {
        return pageType.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: AnalysisPagerListViewHolder, position: Int) {
        val dataSet = getItem(position) ?: return
        with(holder) {
            bind(dataSet, currentList)
        }
    }
}
