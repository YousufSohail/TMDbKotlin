package com.yousufsohail.tmdb.data.source

import android.support.annotation.VisibleForTesting

/**
 * Concrete implementation to load movies from the data sources.
 *
 * Created by Yousuf Sohail on 2/25/18.
 */
class MovieRepository(private val movieRemoteDataSource: MovieDataSource) : MovieDataSource {

    override fun getMovies(page: Int, callback: MovieDataSource.LoadMoviesCallback) {
        movieRemoteDataSource.getMovies(page, callback)
    }

    override fun getMovie(movieId: Int, callback: MovieDataSource.GetMovieCallback) {
        movieRemoteDataSource.getMovie(movieId, callback)
    }

    companion object {

        private var INSTANCE: MovieRepository? = null

        @JvmStatic
        fun getInstance(movieRemoteDataSource: MovieDataSource) =
                INSTANCE ?: synchronized(MovieRepository::class.java) {
                    INSTANCE ?: MovieRepository(movieRemoteDataSource)
                            .also { INSTANCE = it }
                }


        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}
