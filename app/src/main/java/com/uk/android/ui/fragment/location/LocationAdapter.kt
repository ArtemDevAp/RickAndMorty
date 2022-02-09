package com.uk.android.ui.fragment.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.uk.android.databinding.LocationItemBinding
import com.uk.android.model.LocationResponse

class LocationAdapter :
    PagingDataAdapter<LocationResponse, LocationAdapter.LocationViewHolder>(LocationDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = LocationItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LocationViewHolder(
        private val locationItemBinding: LocationItemBinding
    ) : RecyclerView.ViewHolder(locationItemBinding.root) {

        fun bind(locationResponse: LocationResponse?) {
            locationResponse?.let { item ->
                locationItemBinding.name.text = item.name
            }

        }
    }
}

class LocationDiffCallBack : DiffUtil.ItemCallback<LocationResponse>() {
    override fun areItemsTheSame(oldItem: LocationResponse, newItem: LocationResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationResponse, newItem: LocationResponse): Boolean {
        return oldItem == newItem
    }
}