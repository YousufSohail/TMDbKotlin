package com.yousufsohail.tmdb.data

import com.yousufsohail.tmdb.data.source.MovieRepository
import com.yousufsohail.tmdb.data.source.remote.MovieRemoteDataSource
import com.yousufsohail.tmdb.data.source.remote.ServiceGenerator
import com.yousufsohail.tmdb.data.source.remote.Webservice

/**
 * Created by Yousuf Sohail on 2/25/18.
 */
object Injection {

    fun provideMoviesRepository(): MovieRepository {

        val webservice = ServiceGenerator.createService(Webservice::class.java)
        val remoteDataSource = MovieRemoteDataSource.getInstance(webservice)
        return MovieRepository.getInstance(remoteDataSource)
    }
}