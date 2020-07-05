package com.kosmos.kotlincourse.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(val token : String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "token $token")
        requestBuilder.addHeader("Accept", "application/json");
        return chain.proceed(requestBuilder.build())
    }
}