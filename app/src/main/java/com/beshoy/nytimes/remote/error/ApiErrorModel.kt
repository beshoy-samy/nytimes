package com.beshoy.nytimes.remote.error

import android.content.Context
import com.beshoy.nytimes.R
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

data class ApiErrorModel(val type: APIErrorType)

fun Throwable.toApiErrorModel(): ApiErrorModel {
    Timber.e(this)
    return when (this) {
        is ConnectException -> ApiErrorModel(APIErrorType.UNKNOWN)
        is IOException -> ApiErrorModel(APIErrorType.NETWORK)
        is SocketTimeoutException -> ApiErrorModel(APIErrorType.TIME_OUT)
        is HttpException -> {
            when (code()) {
                HttpURLConnection.HTTP_NOT_FOUND -> ApiErrorModel(APIErrorType.NOT_FOUND)
                HttpURLConnection.HTTP_FORBIDDEN, HttpURLConnection.HTTP_UNAUTHORIZED ->
                    ApiErrorModel(APIErrorType.UNAUTHORIZED)
                else -> ApiErrorModel(APIErrorType.UNKNOWN)
            }
        }
        else -> ApiErrorModel(APIErrorType.UNKNOWN)
    }
}

fun ApiErrorModel.getErrorMessage(context: Context): String {
    return when (type) {
        APIErrorType.NETWORK -> context.getString(R.string.network_error)
        APIErrorType.TIME_OUT -> context.getString(R.string.timeout_error)
        APIErrorType.NOT_FOUND -> context.getString(R.string.notfound_error)
        APIErrorType.UNAUTHORIZED -> context.getString(R.string.not_authorized_error)
        else -> context.getString(R.string.unknown_error)
    }
}