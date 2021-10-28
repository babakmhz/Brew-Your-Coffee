package com.babakmhz.coffeeitassessment.view.startup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.babakmhz.coffeeitassessment.R
import com.babakmhz.coffeeitassessment.databinding.FragmentStartUpBinding
import com.babakmhz.coffeeitassessment.view.base.BaseFragment

class StartUpFragment : BaseFragment() {

    private lateinit var binding:FragmentStartUpBinding

    override fun initializeUI() {
        binding.btStart.setOnClickListener {
            findNavController().navigate(R.id.action_startUpFragment_to_productsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartUpBinding.inflate(inflater)
        return binding.root
    }


}