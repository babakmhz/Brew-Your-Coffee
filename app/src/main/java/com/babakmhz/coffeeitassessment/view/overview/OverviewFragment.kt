package com.babakmhz.coffeeitassessment.view.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.babakmhz.coffeeitassessment.R
import com.babakmhz.coffeeitassessment.data.model.device.Type
import com.babakmhz.coffeeitassessment.databinding.FragmentOverviewBinding
import com.babakmhz.coffeeitassessment.view.base.BaseActivity
import com.babakmhz.coffeeitassessment.view.base.BaseFragment
import com.babakmhz.coffeeitassessment.view.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_overview.*


class OverviewFragment : BaseFragment() {

    private lateinit var binding: FragmentOverviewBinding

    private val viewModel: MainViewModel by lazy {
        getSharedViewModel(requireActivity() as BaseActivity, MainViewModel::class.java)
    }

    private fun getOverviewAdapter(items: ArrayList<Type>): OverviewAdapter {
        return OverviewAdapter(requireContext(), items) {
            val action = OverviewFragmentDirections.actionOverviewFragementToOrderFragment(
                it,
                overview = true
            )
            findNavController().navigate(action)
        }
    }

    override fun initializeUI() {
        binding.rclOverview.apply {
            viewModel.orderedType?.let {
                adapter = getOverviewAdapter(arrayListOf(it))
            }
        }

        binding.btBrew.setOnClickListener {
            viewModel.brewYourCoffee()
            findNavController().popBackStack(R.id.productsFragment,false)
        }

        observeTypes()
    }

    private fun observeTypes() = viewModel.overviewTypesLiveData.observe(viewLifecycleOwner) {
        rcl_overview.apply {
            it?.let {
                adapter = getOverviewAdapter(arrayListOf(it))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(inflater)
        return binding.root
    }

}