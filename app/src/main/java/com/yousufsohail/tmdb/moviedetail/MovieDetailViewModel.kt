package com.yousufsohail.tmdb.moviedetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.yousufsohail.tmdb.data.Movie
import com.yousufsohail.tmdb.data.source.MovieDataSource
import com.yousufsohail.tmdb.data.source.MovieRepository

/**
 * Created by Yousuf Sohail on 2/25/18.
 */
class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel(), MovieDataSource.GetMovieCallback {

    val movie = ObservableField<Movie>()

    var isDataLoading = false
        private set

    fun start(movieId: Int?) {
        movieId?.let {
            isDataLoading = true
            movieRepository.getMovie(it, this)
        }
    }

    override fun onMovieLoaded(movie: Movie) {
        this.movie.set(movie)
        isDataLoading = false
    }

    override fun onDataNotAvailable() {
        this.movie.set(null)
        isDataLoading = false
    }
}