package com.babakmhz.coffeeitassessment.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.babakmhz.coffeeitassessment.R
import com.babakmhz.coffeeitassessment.data.model.device.Extra
import com.babakmhz.coffeeitassessment.data.model.device.Size
import com.babakmhz.coffeeitassessment.data.model.device.Type
import com.babakmhz.coffeeitassessment.databinding.FragmentOrderBinding
import com.babakmhz.coffeeitassessment.view.base.BaseActivity
import com.babakmhz.coffeeitassessment.view.base.BaseBottomSheetFragment
import com.babakmhz.coffeeitassessment.view.main.MainViewModel


class OrderFragment : BaseBottomSheetFragment() {

    private lateinit var binding: FragmentOrderBinding
    private val args: OrderFragmentArgs by navArgs()

    private var selectedType = Type()

    private val viewModel: MainViewModel by lazy {
        getSharedViewModel(requireActivity() as BaseActivity, MainViewModel::class.java)
    }

    override fun initializeUI() {
        args.type.let { type ->
            selectedType = type
            binding.rclSize.apply {
                adapter = SizesAdapter(
                    requireContext(),
                    viewModel.getSizesForType(type) as ArrayList<Size>
                ) {
                   selectedType.selectedSize = it
                }
            }

            binding.rclExtras.apply {
                adapter = ExtrasAdapter(
                    requireContext(),
                    viewModel.getExtrasForType(type) as ArrayList<Extra>
                ){
                    selectedType.selectedExtras.add(it)
                }
            }

        }


        binding.btCancel.setOnClickListener {
            dismiss()
        }

        binding.btConfirm.setOnClickListener {
            if (selectedType.selectedSize==null){
                showSnackBar(binding.container,getString(R.string.please_select_one_size))
            }else{
                viewModel.addToWishList(selectedType)
                dismiss()
            }
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