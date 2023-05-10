package io.github.seoj17.presentaion.ui.champion

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.seoj17.presentaion.model.Champion

class ChampionListAdapter(
    private val onChampionClick: (Int) -> Unit,
) : ListAdapter<Champion, ChampionViewHolder>(Champion.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChampionViewHolder(parent)

    override fun onBindViewHolder(holder: ChampionViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bind(data)
            itemView.setOnClickListener {
                onChampionClick(data.key)
            }
        }
    }
}
