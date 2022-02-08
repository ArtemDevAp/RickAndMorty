package com.uk.android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.uk.android.databinding.EpisodeItemBinding
import com.uk.android.model.EpisodeResult

class EpisodesAdapter :
    PagingDataAdapter<EpisodeResult, EpisodesAdapter.EpisodeViewHolder>(EpisodeDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }


    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EpisodeViewHolder(private val binding: EpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(episode: EpisodeResult?) {
            episode?.let {
                binding.name.text = episode.name
            }
        }
    }
}


class EpisodeDiffCallBack : DiffUtil.ItemCallback<EpisodeResult>() {
    override fun areItemsTheSame(oldItem: EpisodeResult, newItem: EpisodeResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodeResult, newItem: EpisodeResult): Boolean {
        return oldItem == newItem
    }
}