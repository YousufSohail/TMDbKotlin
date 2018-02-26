package com.yousufsohail.tmdb.data.source.remote.response

import com.yousufsohail.tmdb.data.Movie


/**
 * Created by Yousuf Sohail on 2/25/18.
 */

data class GetMoviesLatestResponse(
		val page: Int,
		val total_results: Int,
		val total_pages: Int,
		val results: List<Movie>
)