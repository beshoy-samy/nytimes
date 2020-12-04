/*
 * Created by Beshoy Samy on 7/5/20 12:52 AM.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 7/5/20 12:52 AM.
 */

package com.beshoy.nytimes.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import com.beshoy.nytimes.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.google.android.material.imageview.ShapeableImageView


abstract class GlideHelper {

    companion object {

        fun loadImageUri(image: ShapeableImageView, uri: Uri) {
            Glide.with(image)
                .load(uri)
                .placeholder(R.drawable.ic_loading_image)
                .error(R.drawable.ic_broken_image)
                .transition(withCrossFade())
                .into(image)
        }

        fun loadImage(view: ShapeableImageView, url: String?, placeholder: Drawable? = null) {
            Glide.with(view.context).load(url)
                .apply {
                    if (placeholder != null) this.placeholder(placeholder)
                    else this.placeholder(R.drawable.ic_loading_image)
                }
                .error(R.drawable.ic_broken_image)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(view)
        }

    }

}