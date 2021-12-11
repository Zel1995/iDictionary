package com.example.idictionary.model.data.api

import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {

    companion object {
        val interceptor: BaseInterceptor
            get() = BaseInterceptor()
    }

    private var responseCode: Int = 0
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        responseCode = response.code
        return response
    }

    fun getResponseCode(): ServerResponseCode {
        var statusCode =
            ServerResponseCode.UNDEFINED_ERROR
        when (responseCode / 100) {
            1 -> {
                statusCode = ServerResponseCode.INFO
            }
            2 -> {
                statusCode = ServerResponseCode.SUCCESS
            }
            3 -> {
                statusCode = ServerResponseCode.REDIRECTION
            }
            4 -> {
                statusCode = ServerResponseCode.CLIENT_ERROR
            }
            5 -> {
                statusCode = ServerResponseCode.SERVER_ERROR
            }
        }
        return statusCode
    }

    enum class ServerResponseCode {
        INFO,
        SUCCESS,
        REDIRECTION,
        CLIENT_ERROR,
        SERVER_ERROR,
        UNDEFINED_ERROR
    }

}