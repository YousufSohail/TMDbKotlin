package com.yousufsohail.tmdb.data.source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Yousuf Sohail on 2/25/18.
 */
object ServiceGenerator {

    private val API_BASE_URL = "https://api.themoviedb.org/3/"

    private val logging = HttpLoggingInterceptor()

    private val httpBuilder = OkHttpClient.Builder().addInterceptor(logging)

    private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpBuilder.build())

    fun <S> createService(serviceClass: Class<S>): S {
        logging.level = HttpLoggingInterceptor.Level.BODY
        return retrofitBuilder.build().create(serviceClass)
    }
}
