package com.listocalixto.theshoestore.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.listocalixto.theshoestore.app.AppConstants.USER_EMAIL
import com.listocalixto.theshoestore.app.AppConstants.USER_PASSWORD
import com.listocalixto.theshoestore.R
import com.listocalixto.theshoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventUserApproved.observe(viewLifecycleOwner, { isApproved ->
            if (isApproved) {
                findNavController().navigate(LoginFragmentDirections.toWelcomeFragment())
                viewModel.onUserLogIn()
            }
        })

        viewModel.showSnackbarEvent.observe(viewLifecycleOwner, {
            if (it) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.error_login_email_or_pass_incorrect),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.doneShowingSnackbar()
            }
        })

    }

}