package com.beshoy.nytimes.utils

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter(value = ["image_url", "placeholder"], requireAll = false)
fun setImgToIv(iv: ShapeableImageView, url: String?, placeholder: Drawable? = null) {
    GlideHelper.loadImage(iv, url, placeholder)
}