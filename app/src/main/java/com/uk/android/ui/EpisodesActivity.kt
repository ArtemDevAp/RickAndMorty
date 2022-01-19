package com.uk.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.uk.android.data.ApiService
import com.uk.android.databinding.ActivityEpisodesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class EpisodesActivity : AppCompatActivity() {

    @Inject
    lateinit var service: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEpisodesBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.list.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            val episodes = service.getEpisodes()
            binding.list.adapter = EpisodesAdapter(episodes)
        }
    }
}