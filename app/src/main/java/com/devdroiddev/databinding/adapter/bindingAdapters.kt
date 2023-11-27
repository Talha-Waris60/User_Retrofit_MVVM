package com.devdroiddev.databinding.adapter

import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide

// Load the image
@BindingAdapter("imageUrl")
fun loadImageUrl(imageView: ImageView , imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .into(imageView)
}
