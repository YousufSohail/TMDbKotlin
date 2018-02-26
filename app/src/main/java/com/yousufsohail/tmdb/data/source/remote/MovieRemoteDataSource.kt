package com.yousufsohail.tmdb.data.source.remote

import android.support.annotation.VisibleForTesting
import com.yousufsohail.tmdb.common.API_KEY
import com.yousufsohail.tmdb.data.Movie
import com.yousufsohail.tmdb.data.source.MovieDataSource
import com.yousufsohail.tmdb.data.source.remote.response.APIError
import com.yousufsohail.tmdb.data.source.remote.response.GetMoviesLatestResponse
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Yousuf Sohail on 2/25/18.
 */
class MovieRemoteDataSource(private val webservice: Webservice): MovieDataSource {

    override fun getMovies(page: Int, callback: MovieDataSource.LoadMoviesCallback) {
        val call = webservice.getMoviesLatest(API_KEY, page = 1)
        call.enqueue(object : Callback<GetMoviesLatestResponse>(){
            override fun onSuccess(call: Call<GetMoviesLatestResponse>, response: Response<GetMoviesLatestResponse>) {
                callback.onMoviesLoaded(response.body()!!)
            }

            override fun onFail(throwable: Throwable, error: APIError) {
                callback.onDataNotAvailable()
            }

        })
    }

    override fun getMovie(movieId: Int, callback: MovieDataSource.GetMovieCallback) {
        val call = webservice.getMovieDetail(movieId, API_KEY)
        call.enqueue(object : Callback<Movie>(){
            override fun onSuccess(call: Call<Movie>, response: Response<Movie>) {
                callback.onMovieLoaded(response.body()!!)
            }

            override fun onFail(throwable: Throwable, error: APIError) {

            }

        })
    }

    companion object {
        private var INSTANCE: MovieRemoteDataSource? = null

        @JvmStatic
        fun getInstance(webservice: Webservice): MovieRemoteDataSource {
            if (INSTANCE == null) {
                synchronized(MovieRemoteDataSource::javaClass) {
                    INSTANCE = MovieRemoteDataSource(webservice)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}