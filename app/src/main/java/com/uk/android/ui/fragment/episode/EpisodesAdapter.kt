package com.uk.android.ui.fragment.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.uk.android.databinding.EpisodeItemBinding
import com.uk.android.model.EpisodeResponse

class EpisodesAdapter :
    PagingDataAdapter<EpisodeResponse, EpisodesAdapter.EpisodeViewHolder>(EpisodeDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = EpisodeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EpisodeViewHolder(private val binding: EpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(episode: EpisodeResponse?) {
            episode?.let {
                binding.name.text = episode.name
            }
        }
    }
}


class EpisodeDiffCallBack : DiffUtil.ItemCallback<EpisodeResponse>() {
    override fun areItemsTheSame(oldItem: EpisodeResponse, newItem: EpisodeResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodeResponse, newItem: EpisodeResponse): Boolean {
        return oldItem == newItem
    }
}