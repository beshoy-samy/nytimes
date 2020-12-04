package com.beshoy.nytimes.koin

import androidx.databinding.library.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val NETWORK_CONNECT_TIMEOUT = 30L
private const val NETWORK_READ_TIMEOUT = 30L
private const val NETWORK_WRITE_TIMEOUT = 30L

val networkModule = module {

    fun buildOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor.Logger { message -> Timber.i(message) }

        val loggingInterceptor =
            HttpLoggingInterceptor(logger).apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .build()
                return@Interceptor chain.proceed(request)
            })
            .addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                return@addInterceptor response
            }
            .connectTimeout(NETWORK_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .apply { if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor) }
            .build()
    }

    single {
        buildOkHttpClient()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://api.nytimes.com/svc/mostpopular/v2/")
            .client(get())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
    }

}