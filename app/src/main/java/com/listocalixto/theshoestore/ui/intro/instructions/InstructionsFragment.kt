package com.listocalixto.theshoestore.ui.intro.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.listocalixto.theshoestore.R
import com.listocalixto.theshoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment(R.layout.fragment_instructions) {

    private val viewModel by viewModels<InstructionsViewModel>()
    private lateinit var binding: FragmentInstructionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInstructionsBinding.bind(view)

        binding.instructionViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventToList.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(InstructionsFragmentDirections.toShoesListFragment())
                viewModel.eventNavigateFinish()
            }
        })

    }

}