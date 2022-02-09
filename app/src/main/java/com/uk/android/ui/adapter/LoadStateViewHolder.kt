package com.uk.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.uk.android.R
import com.uk.android.databinding.LoadStateItemBinding

class LoadStateViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.load_state_item, parent, false
    )
) {
    private val binding = LoadStateItemBinding.bind(itemView)
    private val progress: CircularProgressIndicator = binding.progress

    fun bind(loadState: LoadState) {
        progress.isVisible = loadState is LoadState.Loading
    }
}