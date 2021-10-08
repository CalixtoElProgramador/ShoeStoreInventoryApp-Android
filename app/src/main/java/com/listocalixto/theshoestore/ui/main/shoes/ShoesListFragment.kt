package com.listocalixto.theshoestore.ui.main.shoes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.listocalixto.theshoestore.R
import com.listocalixto.theshoestore.databinding.FragmentShoesListBinding
import com.listocalixto.theshoestore.databinding.LayoutItemShoeBinding
import com.listocalixto.theshoestore.ui.main.ShoeViewModel

class ShoesListFragment : Fragment(R.layout.fragment_shoes_list) {

    private val viewModel by activityViewModels<ShoeViewModel>()
    private lateinit var binding: FragmentShoesListBinding
    private lateinit var bindingShoe: LayoutItemShoeBinding

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShoesListBinding.bind(view)
        binding.shoeViewModel = viewModel

        viewModel.eventToDetails.observe(viewLifecycleOwner, { isFinished ->
            if (isFinished) {
                findNavController().navigate(ShoesListFragmentDirections.toShoeDetailFragment())
                viewModel.onDetailsFragment()
            }
        })

        viewModel.shoeList.observe(viewLifecycleOwner, { list ->
            list.forEachIndexed { index, shoe ->
                val itemShoe = layoutInflater.inflate(R.layout.layout_item_shoe, null)
                bindingShoe = LayoutItemShoeBinding.bind(itemShoe)
                bindingShoe.apply {
                    textShoeName.text = shoe.name
                    textShoeSize.text = "Size: ${shoe.size}"
                    textShoeCompany.text = shoe.company
                    textShoeDescription.text = shoe.description
                }
                binding.layoutShoeList.addView(itemShoe, index)
            }
        })

        viewModel.eventLogout.observe(viewLifecycleOwner, { isFinished ->
            if (isFinished) {
                findNavController().navigate(ShoesListFragmentDirections.toLoginFragment())
                viewModel.onLoginFragment()
            }
        })

        binding.topAppBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.logout -> {
                    viewModel.onLogout()
                    true
                }
                else -> {
                    false
                }
            }
        }

    }

}