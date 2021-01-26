package ua.kolot.myacademyproject.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val API_KEY = "8a07f2cb96a54f2137849d2d3c9fe320"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl =
            request.url.newBuilder().addQueryParameter(QUERY_PARAM_API_KEY, API_KEY).build()
        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }
}