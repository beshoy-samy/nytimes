package com.beshoy.nytimes.utils

fun Long?.orNotFound(): Long = this ?: AppConstants.NOT_FOUND

fun String.Companion.empty() = ""