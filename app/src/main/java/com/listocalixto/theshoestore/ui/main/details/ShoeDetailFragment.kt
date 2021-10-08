package com.listocalixto.theshoestore.ui.main.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.listocalixto.theshoestore.R
import com.listocalixto.theshoestore.data.model.Shoe
import com.listocalixto.theshoestore.databinding.FragmentShoeDetailBinding
import com.listocalixto.theshoestore.ui.main.ShoeViewModel

class ShoeDetailFragment : Fragment(R.layout.fragment_shoe_detail) {

    private val viewModel by activityViewModels<ShoeViewModel>()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoeDetailBinding.bind(view)

        binding.buttonAddShoe.setOnClickListener { verifyInputs() }
        binding.topAppBar.setNavigationOnClickListener { activity?.onBackPressed() }

        viewModel.eventAddShoe.observe(viewLifecycleOwner, { isAdded ->
            if (isAdded) {
                activity?.onBackPressed()
                viewModel.onShoeAdded()
            }
        })

    }

    private fun verifyInputs() {
        val name = binding.inputShoeName.text.toString()
        val size = binding.inputShoeSize.text.toString()
        val company = binding.inputShoeCompany.text.toString()
        val description = binding.inputShoeDescription.text.toString()
        val imageLink = binding.inputShoeLinks.text.toString()
        when {
            name.isNotEmpty() && size.isNotEmpty() && company.isNotEmpty() && description.isNotEmpty() && imageLink.isNotEmpty() -> {
                viewModel.addShoe(Shoe(name, size.toDouble(), company, description, arrayListOf(imageLink, imageLink)))
                viewModel.onShoeApproved()
            }
            
            else -> {
                Toast.makeText(context, "Fill all entries", Toast.LENGTH_SHORT).show()
            }
            
        }
    }

}