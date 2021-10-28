package com.babakmhz.coffeeitassessment.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.babakmhz.coffeeitassessment.R
import com.bumptech.glide.Glide

class AppUtils {

    object CustomBindingAdapter {
        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            try {
                Glide.with(view).load(url).placeholder(R.drawable.coffee_startup).into(view)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @BindingAdapter("bindChoosingBackground")
        @JvmStatic
        fun bindChoosingBackground(
            view: View, condition: Boolean,
        ) {
            if (condition) {
                view.setBackgroundResource(R.drawable.shape_green_8r_no_padding)
            } else {
                view.setBackgroundResource(R.drawable.shape_gray_8r)
            }
        }


        @BindingAdapter("condition", "selectedTextColor", "defaultTextColor")
        @JvmStatic
        fun bindChoosingTextColor(
            view: TextView, condition: Boolean,
            @DrawableRes selectedTextColor: Int,
            @DrawableRes defaultTextColor: Int
        ) {

            if (condition) {
                view.setTextColor(selectedTextColor)
            } else {
                view.setTextColor(defaultTextColor)
            }
        }
    }


}