package com.yousufsohail.tmdb.movielist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.yousufsohail.tmdb.data.Movie
import com.yousufsohail.tmdb.data.source.MovieDataSource
import com.yousufsohail.tmdb.data.source.MovieRepository
import com.yousufsohail.tmdb.data.source.remote.response.GetMoviesLatestResponse

/**
 * Created by Yousuf Sohail on 2/25/18.
 */
class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel(), MovieDataSource.LoadMoviesCallback {

    private val TAG = this::class.java.simpleName

    val items = MutableLiveData<List<Movie>>()
    var page: Int = 1
    var totalResults: Int = 0
    private val visibleThreshold = 1

    fun start() {
        loadMovies()
    }

    private fun loadMovies() {
        movieRepository.getMovies(page, this)
    }

    override fun onMoviesLoaded(result: GetMoviesLatestResponse) {

        with(items) {
            value?.let {
                it.plus(result.results)
                return
            }

            value = result.results
            totalResults = result.total_results

        }
    }

    override fun onDataNotAvailable() {
        Log.d(TAG, "Data not available.")
    }

    fun loadMore(itemCount: Int, lastVisibleItem: Int) {

        if (itemCount < totalResults) {
            if (itemCount <= lastVisibleItem + visibleThreshold) {
                page++
                loadMovies()
            }
        }

    }
}
