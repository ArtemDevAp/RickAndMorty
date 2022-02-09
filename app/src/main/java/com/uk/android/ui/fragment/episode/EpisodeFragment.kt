package com.uk.android.ui.fragment.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.uk.android.databinding.EpisodeFragmentBinding
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

        snackBar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)

        binding.episodeList.run {
            setHasFixedSize(true)
            adapter = episodeAdapter
        }

        viewModel.episodeHandler(episodeAdapter.loadStateFlow)

        viewModel.observe(
            lifecycleOwner = viewLifecycleOwner,
            state = ::render,
            sideEffect = ::sideEffect
        )
    }

    private suspend fun render(episodeState: EpisodeState) {

        episodeState.episodeResponse?.let { episodeData ->
            episodeAdapter.submitData(episodeData)
        }

        binding.progressLoad.isVisible = episodeState.loading
    }

    private fun sideEffect(episodeSideEffect: EpisodeSideEffect) {
        when (episodeSideEffect) {
            is EpisodeSideEffect.ToastError -> {
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