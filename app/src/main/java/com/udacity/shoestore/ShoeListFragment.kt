package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            // bind this fragment with layout to use any function of it.
            shoeListFragment = this@ShoeListFragment
            addButton.setOnClickListener {
                moveToShoeDetail()
            }
        }

        setHasOptionsMenu(true)
        shoeListView(sharedViewModel.shoeList)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return findNavController().navigateUp() || super.onOptionsItemSelected(item)
    }

    private fun moveToShoeDetail() {
        sharedViewModel.propertyReset()
        findNavController().navigate(R.id.action_shoeListFragment_to_detailsFragment)
    }
    /*
     add view function which is inflate item layout
     for each (element in ShoeList object) inside linear layout of shoe list fragment
     */
    private fun shoeListView(shoe: MutableList<Shoe>) {
        shoe.forEach {
            val itemBinding = ShoeItemBinding.inflate(layoutInflater)
            itemBinding.apply {
                shoeName.text = it.name
                shoeSize.text = it.size.toString()
                shoeCompany.text = it.company
                shoeDescription.text = it.description
            }
            binding.listItem.addView(itemBinding.root)

        }
    }
}