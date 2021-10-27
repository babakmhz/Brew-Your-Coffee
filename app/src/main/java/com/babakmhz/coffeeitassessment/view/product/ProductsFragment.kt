package com.babakmhz.coffeeitassessment.view.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.babakmhz.coffeeitassessment.R
import com.babakmhz.coffeeitassessment.data.model.device.Type
import com.babakmhz.coffeeitassessment.databinding.FragmentProductsBinding
import com.babakmhz.coffeeitassessment.utils.State
import com.babakmhz.coffeeitassessment.view.base.BaseActivity
import com.babakmhz.coffeeitassessment.view.base.BaseFragment
import com.babakmhz.coffeeitassessment.view.main.MainViewModel
import timber.log.Timber


class ProductsFragment : BaseFragment() {

    private lateinit var binding: FragmentProductsBinding
    private lateinit var productsAdapter: ProductsAdapter

    private val viewModel: MainViewModel by lazy {
        getSharedViewModel(requireActivity() as BaseActivity, MainViewModel::class.java)
    }

    override fun initializeUI() {
        binding.rclProducts.apply {
            productsAdapter = ProductsAdapter(requireContext(), arrayListOf()) {
            }

            this.adapter = productsAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTypes()
        observeTypesLiveData()

    }


    private fun observeTypesLiveData() = viewModel.typesLivedata.observe(viewLifecycleOwner) {
        when (it) {
            is State.Error -> {
                hideLoadingDialog(this::class.java.simpleName)
                showErrorSnackBar(requireView())
            }
            State.Loading -> showLoadingDialog(this::class.java.simpleName)
            is State.Success -> {
                hideLoadingDialog(this::class.java.simpleName)
                productsAdapter.addItems(it.data)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater)
        return binding
            .root
    }

}