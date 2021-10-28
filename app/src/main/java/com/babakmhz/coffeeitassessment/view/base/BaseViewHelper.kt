package com.babakmhz.coffeeitassessment.view.base

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

interface BaseViewHelper {

    fun <T : ViewModel> getSharedViewModel(activity: BaseActivity, viewModel: Class<T>): T {
        return ViewModelProvider(activity).get(viewModel)
    }


    fun showErrorSnackBar(view: View) {
        Snackbar.make(view, "Error occurred!", Snackbar.LENGTH_LONG).setAction("OK") {
        }.show()
    }

    fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("OK") {
        }.show()
    }

    fun animateViewFromResource(view: View, context: Context, animId: Int) {
        val animation =
            AnimationUtils.loadAnimation(context, animId)
        view.startAnimation(animation)
    }
}