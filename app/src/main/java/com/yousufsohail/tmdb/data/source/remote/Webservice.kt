package com.yousufsohail.tmdb.data.source.remote

import com.yousufsohail.tmdb.data.Movie
import com.yousufsohail.tmdb.data.source.remote.response.GetMoviesLatestResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Yousuf Sohail on 2/25/18.
 */
interface Webservice {

    @GET("discover/movie")
    fun getMoviesLatest(@Query("api_key") apiKey: String,
                        @Query("language") language: String = "en-US",
                        @Query("sort_by") sort_by: String = "release_date.desc",
                        @Query("include_adult") includeAdult: Boolean = false,
                        @Query("page") page: Int,
                        @Query("release_date.lte") releaseDate: String = "2018-2-25")
            : Call<GetMoviesLatestResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int,
                       @Query("api_key") apiKey: String)
            : Call<Movie>
}