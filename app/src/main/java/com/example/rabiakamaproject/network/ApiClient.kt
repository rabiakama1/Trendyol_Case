package com.example.rabiakamaproject.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    object Constant {
        var BASE_URL = "https://browsingpublic.trendyol.com/"
    }

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit =
                Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(
                    GsonConverterFactory.create()
                ).build()
        return retrofit as Retrofit
    }

}