package com.uk.android.ui.fragment.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.uk.android.databinding.CharacterItemBinding
import com.uk.android.model.CharacterResponse

class CharacterAdapter :
    PagingDataAdapter<CharacterResponse, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CharacterViewHolder(
        private val characterItemBinding: CharacterItemBinding
    ) : RecyclerView.ViewHolder(characterItemBinding.root) {

        fun bind(characterResponse: CharacterResponse?) {
            characterResponse?.let {
                characterItemBinding.name.text = characterResponse.name
            }
        }
    }
}

class CharacterDiffCallBack : DiffUtil.ItemCallback<CharacterResponse>() {
    override fun areItemsTheSame(oldItem: CharacterResponse, newItem: CharacterResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharacterResponse,
        newItem: CharacterResponse
    ): Boolean {
        return oldItem == newItem
    }
}