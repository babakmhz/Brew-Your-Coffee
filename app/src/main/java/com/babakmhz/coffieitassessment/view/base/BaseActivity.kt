package com.babakmhz.coffieitassessment.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babakmhz.coffieitassessment.utils.LoadingContainer
import com.babakmhz.coffieitassessment.view.dialog.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity(), LoadingContainer,BaseViewHelper {
    private var loadingDialog: LoadingDialog? = null
    private val loadingIds = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initializeUi()
    }

    abstract fun initializeUi()

    override fun getLoadingDialog(): LoadingDialog {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        return loadingDialog!!
    }

    override fun getLoadingIdsArray(): ArrayList<Int> {
        return loadingIds
    }


    override fun setLoadingDialogToNull() {
        loadingDialog = null
    }
}