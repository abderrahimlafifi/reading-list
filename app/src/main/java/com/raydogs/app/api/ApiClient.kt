package com.raydogs.app.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    const val BASE_URL = "https://raydogs.com/"

    private val userAgentInterceptor = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 10) RayDogsApp/1.0")
                .build()
        )
    }

    private val http = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(userAgentInterceptor)
        .build()

    val api: WordPressApi = Retrofit.Builder()
        .baseUrl("${BASE_URL}wp-json/")
        .client(http)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WordPressApi::class.java)
}
