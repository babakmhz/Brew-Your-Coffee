package com.babakmhz.coffeeitassessment.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.babakmhz.coffeeitassessment.utils.LoadingContainer
import com.babakmhz.coffeeitassessment.view.dialog.LoadingDialog
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