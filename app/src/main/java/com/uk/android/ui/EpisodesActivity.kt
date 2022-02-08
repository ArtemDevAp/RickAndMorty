package com.uk.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.uk.android.databinding.ActivityEpisodesBinding
import com.uk.android.repository.RickAndMortyRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class EpisodesActivity : AppCompatActivity() {

    @Inject
    lateinit var rickAndMortyRepository: RickAndMortyRepository

    private val episodeAdapter: EpisodesAdapter by lazy {
        EpisodesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEpisodesBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.list.layoutManager = LinearLayoutManager(this)

        binding.list.adapter = episodeAdapter

        lifecycleScope.launch {
            rickAndMortyRepository.getEpisodes().collectLatest {
                episodeAdapter.submitData(it)
            }
        }
    }
}