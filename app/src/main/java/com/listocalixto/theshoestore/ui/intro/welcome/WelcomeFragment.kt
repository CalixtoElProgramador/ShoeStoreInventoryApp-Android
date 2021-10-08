package com.listocalixto.theshoestore.ui.intro.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.listocalixto.theshoestore.R
import com.listocalixto.theshoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private val viewModel by viewModels<WelcomeViewModel>()
    private lateinit var binding: FragmentWelcomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)

        binding.welcomeViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventNextFragment.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(WelcomeFragmentDirections.toInstructionsFragment())
                viewModel.eventNavigateFinish()
            }
        })

    }

}