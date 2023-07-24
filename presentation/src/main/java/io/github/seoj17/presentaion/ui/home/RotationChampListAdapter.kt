package io.github.seoj17.presentaion.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.github.seoj17.presentaion.model.RotationChamp

class RotationChampListAdapter(
    private val itemClickListener: (Int) -> Unit,
) : ListAdapter<RotationChamp, RotationChampViewHolder>(RotationChamp.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RotationChampViewHolder(parent)

    override fun onBindViewHolder(holder: RotationChampViewHolder, position: Int) {
        val dataSet = getItem(position)
        with(holder) {
            bind(dataSet)
            itemView.setOnClickListener {
                itemClickListener(dataSet.key)
            }
        }
    }
}
