package io.github.seoj17.canyongg.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class RotationChampListAdapter :
    ListAdapter<String, RotationChampViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RotationChampViewHolder(parent)

    override fun onBindViewHolder(holder: RotationChampViewHolder, position: Int) {
        val dataSet = getItem(position)
        with(holder) {
            bind(dataSet)
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
