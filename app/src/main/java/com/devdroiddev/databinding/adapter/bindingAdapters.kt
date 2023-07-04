package com.devdroiddev.databinding.adapter

import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun loadImageUrl(imageView: ImageView , imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .into(imageView)
}

/*
@BindingAdapter("imageUrl", "new")
fun ImageView.loadImageUrl(imageUrl: String?, new: String?) {
    Glide.with(this.context)
        .load(imageUrl)
        .into(this)
}*/
