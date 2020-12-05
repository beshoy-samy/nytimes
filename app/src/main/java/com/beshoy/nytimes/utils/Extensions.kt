package com.beshoy.nytimes.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun Long?.orNotFound(): Long = this ?: AppConstants.NOT_FOUND

fun String.Companion.empty() = ""

fun RecyclerView.setDivider(@DrawableRes drawableRes: Int, orientation: Int) {
    val divider = DividerItemDecoration(this.context, orientation)
    val drawable = ContextCompat.getDrawable(this.context, drawableRes)
    drawable?.let {
        divider.setDrawable(it)
        addItemDecoration(divider)
    }
}

fun Context.openExternalUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    this.startActivity(intent)
}