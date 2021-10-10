package com.listocalixto.theshoestore.ui.main.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.listocalixto.theshoestore.R
import com.listocalixto.theshoestore.databinding.FragmentShoeDetailBinding
import com.listocalixto.theshoestore.ui.main.ShoeViewModel

class ShoeDetailFragment : Fragment(R.layout.fragment_shoe_detail) {

    private val viewModel by activityViewModels<ShoeViewModel>()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoeDetailBinding.bind(view)
        binding.shoeViewModel = viewModel

        binding.topAppBar.setNavigationOnClickListener { activity?.onBackPressed() }

        viewModel.eventAddShoe.observe(viewLifecycleOwner, { isAdded ->
            if (isAdded) {
                activity?.onBackPressed()
                viewModel.onShoeAdded()
            }
        })

        viewModel.showSnackbarEvent.observe(viewLifecycleOwner, {
            if (it) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.error_fill_all_fields),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.doneShowingSnackbar()
            }
        })



    }

}