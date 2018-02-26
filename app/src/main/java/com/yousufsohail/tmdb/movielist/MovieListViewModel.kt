package com.yousufsohail.tmdb.movielist

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import com.yousufsohail.tmdb.data.Movie
import com.yousufsohail.tmdb.data.source.MovieDataSource
import com.yousufsohail.tmdb.data.source.MovieRepository
import com.yousufsohail.tmdb.data.source.remote.response.GetMoviesLatestResponse
import java.util.*

/**
 * Created by Yousuf Sohail on 2/25/18.
 */
class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel(), MovieDataSource.LoadMoviesCallback {

    private val TAG = this::class.java.simpleName

    val items: ObservableList<Movie> = ObservableArrayList()
    var page: Int = 1
    var totalResults: Int = 0
    private val visibleThreshold = 1
    val calendar: Calendar = Calendar.getInstance()

    fun start() {
        loadMovies()
    }

    private fun loadMovies() {
        val date = "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)}"
        movieRepository.getMovies(date, page, this)
    }

    override fun onMoviesLoaded(result: GetMoviesLatestResponse) {
        with(items) {
            totalResults = result.total_results
            addAll(result.results)
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

    fun setDate(year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        with(items) {
            clear()
        }
        page = 1
        loadMovies()
    }
}
