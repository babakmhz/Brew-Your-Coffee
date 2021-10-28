package com.babakmhz.coffeeitassessment.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.babakmhz.coffeeitassessment.R
import com.babakmhz.coffeeitassessment.data.model.device.Size
import com.babakmhz.coffeeitassessment.databinding.FragmentOrderBinding
import com.babakmhz.coffeeitassessment.view.base.BaseActivity
import com.babakmhz.coffeeitassessment.view.base.BaseBottomSheetFragment
import com.babakmhz.coffeeitassessment.view.main.MainViewModel


class OrderFragment : BaseBottomSheetFragment() {

    private lateinit var binding: FragmentOrderBinding
    private val args: OrderFragmentArgs by navArgs()

    private val viewModel: MainViewModel by lazy {
        getSharedViewModel(requireActivity() as BaseActivity, MainViewModel::class.java)
    }

    override fun initializeUI() {
        args.type.let {
            binding.rclSize.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = SizesAdapter(requireContext(),
                    viewModel.getSizesForType(it) as ArrayList<Size>
                ) {

                }
            }


        }


        binding.btCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun getTheme(): Int {
        return R.style.BaseBottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater)
        binding.type = args.type
        binding.executePendingBindings()
        return binding.root
    }


}