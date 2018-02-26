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

    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    private var retrofit = builder.build()

    fun <S> createService(serviceClass: Class<S>): S {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)

        return retrofit.create(serviceClass)
    }
}
