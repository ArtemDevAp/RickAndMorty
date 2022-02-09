package com.uk.android.ui.fragment.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.uk.android.R
import com.uk.android.databinding.LocationFragmentBinding
import com.uk.android.ui.adapter.SpinnerLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class LocationFragment : Fragment() {

    private val viewModel: LocationViewModel by viewModels()

    private var _binding: LocationFragmentBinding? = null

    private val binding get() = _binding!!

    private var snackBar: Snackbar? = null

    private val locationAdapter: LocationAdapter = LocationAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LocationFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackBar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
            .setAnchorView(R.id.bottom_navigation)

        binding.locationList.run {
            adapter = locationAdapter.withLoadStateHeaderAndFooter(
                header = SpinnerLoadStateAdapter(),
                footer = SpinnerLoadStateAdapter()
            )
            setHasFixedSize(true)
        }

        viewModel.observe(viewLifecycleOwner, ::render, ::sideEffect)

    }

    private suspend fun render(locationState: LocationState) {

        binding.progressLoad.isVisible = locationState.loading

        locationState.locations?.let { list ->
            locationAdapter.submitData(list)
        }

    }

    private fun sideEffect(locationSideEffect: LocationSideEffect) {
        when (locationSideEffect) {
            is LocationSideEffect.SnackError -> {
                snackBar?.run {
                    setText(locationSideEffect.msg)
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