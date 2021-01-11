package ua.kolot.myacademyproject.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url.newBuilder().addQueryParameter("api_key", "8a07f2cb96a54f2137849d2d3c9fe320").build()
        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }
}