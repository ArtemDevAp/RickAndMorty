package com.uk.android.ui.fragment.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.uk.android.databinding.CharacterFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var _binding: CharacterFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CharacterViewModel by viewModels()

    private val characterAdapter: CharacterAdapter = CharacterAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CharacterFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.characterList.run {
            adapter = characterAdapter
            setHasFixedSize(true)
        }

        viewModel.observe(viewLifecycleOwner, ::render, ::sideEffect)

    }

    private suspend fun render(characterState: CharacterState) {

        characterState.character?.let { characters ->
            characterAdapter.submitData(characters)
        }

        binding.progressLoad.isVisible = characterState.loading
    }

    private fun sideEffect(characterSideEffect: CharacterSideEffect) {

    }
}