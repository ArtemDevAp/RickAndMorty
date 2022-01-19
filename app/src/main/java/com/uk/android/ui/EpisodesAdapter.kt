package com.uk.android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uk.android.data.Episodes
import com.uk.android.data.Result
import com.uk.android.databinding.EpisodeItemBinding


class EpisodesAdapter(
    private val episodes: Episodes
) : RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return episodes.results.size
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes.results[position])
    }

    class EpisodeViewHolder(private val binding: EpisodeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(episode: Result) {
            binding.name.text = episode.name
        }
    }
}
