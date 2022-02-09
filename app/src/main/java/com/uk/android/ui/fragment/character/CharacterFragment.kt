package com.uk.android.ui.fragment.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.uk.android.R
import com.uk.android.databinding.CharacterFragmentBinding
import com.uk.android.ui.adapter.SpinnerLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.character_fragment) {

    private var _binding: CharacterFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CharacterViewModel by viewModels()

    private val characterAdapter: CharacterAdapter = CharacterAdapter()

    private var snackBar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CharacterFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackBar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
            .setAnchorView(R.id.bottom_navigation)

        binding.characterList.run {
            adapter = characterAdapter.withLoadStateHeaderAndFooter(
                header = SpinnerLoadStateAdapter(),
                footer = SpinnerLoadStateAdapter()
            )
            setHasFixedSize(true)
        }

        viewModel.observe(viewLifecycleOwner, ::render, ::sideEffect)

    }

    private suspend fun render(characterState: CharacterState) {

        binding.progressLoad.isVisible = characterState.loading

        characterState.character?.let { characters ->
            characterAdapter.submitData(characters)
        }
    }

    private fun sideEffect(characterSideEffect: CharacterSideEffect) {
        when (characterSideEffect) {
            is CharacterSideEffect.SnackError -> {
                snackBar?.run {
                    setText(characterSideEffect.msg)
                    show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        snackBar?.dismiss()
        snackBar = null
    }
}