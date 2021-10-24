package com.babakmhz.coffieitassessment.view.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.babakmhz.coffieitassessment.view.dialog.LoadingDialog
import com.babakmhz.coffieitassessment.utils.LoadingContainer
import java.util.*

abstract class BaseFragment : Fragment(), LoadingContainer,BaseViewHelper {
    private var loadingDialog: LoadingDialog? = null
    private val loadingIds = ArrayList<Int>()


    override fun getLoadingDialog(): LoadingDialog {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(requireContext())
        }
        return loadingDialog!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        initializeViewModel()
    }

    abstract fun initializeViewModel()
    abstract fun initializeUI()


    override fun getLoadingIdsArray(): ArrayList<Int> {
        return loadingIds
    }

    override fun setLoadingDialogToNull() {
        loadingDialog = null
    }

}