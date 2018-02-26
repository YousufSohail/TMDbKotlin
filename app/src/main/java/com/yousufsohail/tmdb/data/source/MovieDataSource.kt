package com.yousufsohail.tmdb.data.source

import com.yousufsohail.tmdb.data.Movie
import com.yousufsohail.tmdb.data.source.remote.response.GetMoviesLatestResponse

/**
 * Main entry point for accessing movies data.
 *
 * Created by Yousuf Sohail on 2/25/18.
 *
 */
interface MovieDataSource {

    interface LoadMoviesCallback {

        fun onMoviesLoaded(result: GetMoviesLatestResponse)

        fun onDataNotAvailable()
    }

    interface GetMovieCallback {

        fun onMovieLoaded(movie: Movie)

        fun onDataNotAvailable()
    }

    fun getMovies(page: Int, callback: LoadMoviesCallback)

    fun getMovie(movieId: Int, callback: GetMovieCallback)
    
}