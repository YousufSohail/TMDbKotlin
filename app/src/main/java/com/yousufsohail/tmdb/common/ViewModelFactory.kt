package com.yousufsohail.tmdb.common

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import com.yousufsohail.tmdb.data.Injection
import com.yousufsohail.tmdb.data.source.MovieRepository
import com.yousufsohail.tmdb.moviedetail.MovieDetailViewModel
import com.yousufsohail.tmdb.movielist.MovieListViewModel

/**
 * Created by Yousuf Sohail on 2/25/18.
 */
class ViewModelFactory private constructor(private val movieRepository: MovieRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MovieListViewModel::class.java) -> MovieListViewModel(movieRepository)
                    isAssignableFrom(MovieDetailViewModel::class.java) -> MovieDetailViewModel(movieRepository)
                    else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(
                            Injection.provideMoviesRepository()
                    )
                            .also { INSTANCE = it }
                }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}