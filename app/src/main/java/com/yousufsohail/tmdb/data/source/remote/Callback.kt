package com.yousufsohail.tmdb.data.source.remote

import com.yousufsohail.tmdb.data.source.remote.response.APIError
import retrofit2.Call
import retrofit2.Response

/**
 * Created by ysohail on 2/15/18.
 */


abstract class Callback<T> : retrofit2.Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (!response.isSuccessful) {
            onFail(Throwable(), APIError(response.message(), response.code()))
        } else {
            onSuccess(call, response)
        }
    }

    override fun onFailure(call: Call<T>, throwable: Throwable) {
        onFail(throwable, APIError("Oops! something went wrong", 500))
    }

    abstract fun onSuccess(call: Call<T>, response: Response<T>)

    abstract fun onFail(throwable: Throwable, error: APIError)
}
