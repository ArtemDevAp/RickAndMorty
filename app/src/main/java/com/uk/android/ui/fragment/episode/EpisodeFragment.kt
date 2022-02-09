package com.uk.android.ui.fragment.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.uk.android.R
import com.uk.android.databinding.EpisodeFragmentBinding
import com.uk.android.ui.adapter.SpinnerLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class EpisodeFragment : Fragment() {

    private val viewModel: EpisodeViewModel by viewModels()

    private var _binding: EpisodeFragmentBinding? = null

    private val binding get() = _binding!!

    private val episodeAdapter: EpisodesAdapter = EpisodesAdapter()

    private var snackBar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EpisodeFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackBar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE)
            .setAnchorView(R.id.bottom_navigation)
            .setAction("Refresh") {
                episodeAdapter.retry()
            }

        binding.episodeList.run {
            adapter = episodeAdapter.withLoadStateHeaderAndFooter(
                header = SpinnerLoadStateAdapter(),
                footer = SpinnerLoadStateAdapter()
            )
            setHasFixedSize(true)
        }

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::render,
            sideEffect = ::sideEffect
        )

        viewModel.episodeHandler(episodeAdapter.loadStateFlow)
    }

    private suspend fun render(episodeState: EpisodeState) {

        binding.progressLoad.isVisible = episodeState.loading

        episodeState.episodeResponse?.let { episodeData ->
            episodeAdapter.submitData(episodeData)
        }
    }

    private fun sideEffect(episodeSideEffect: EpisodeSideEffect) {
        when (episodeSideEffect) {
            is EpisodeSideEffect.SnackError -> {
                snackBar?.run {
                    setText(episodeSideEffect.msg)
                    show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        snackBar?.dismiss()
        snackBar = null
        _binding = null
    }
}