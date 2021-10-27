package com.babakmhz.coffeeitassessment.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.babakmhz.coffeeitassessment.R
import com.bumptech.glide.Glide

class AppUtils {

    object LoadImageBindingAdapter {
        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            try {
                Glide.with(view).load(url).placeholder(R.drawable.coffee_startup).into(view)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}