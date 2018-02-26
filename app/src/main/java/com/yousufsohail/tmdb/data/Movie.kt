package com.yousufsohail.tmdb.data

/**
 * Created by Yousuf Sohail on 2/25/18.
 */

data class Movie(
		val adult: Boolean,
		val backdrop_path: String,
		val belongs_to_collection: Any,
		val budget: Int,
		val genres: List<Genre>,
		val homepage: String,
		val id: Int,
		val imdb_id: Any,
		val original_language: String,
		val original_title: String,
		val overview: String,
		val popularity: Double,
		val poster_path: String,
		val production_companies: List<Any>,
		val production_countries: List<ProductionCountry>,
		val release_date: String,
		val revenue: Int,
		val runtime: Int,
		val spoken_languages: List<Any>,
		val status: String,
		val tagline: String,
		val title: String,
		val video: Boolean,
		val vote_average: Int,
		val vote_count: Int
)

data class Genre(
		val id: Int,
		val name: String
)

data class ProductionCountry(
		val iso_3166_1: String,
		val name: String
)



//data class Result(
//		val vote_count: Int,
//		val id: Int,
//		val video: Boolean,
//		val vote_average: Int,
//		val title: String,
//		val popularity: Double,
//		val poster_path: String,
//		val original_language: String,
//		val original_title: String,
//		val genre_ids: List<Int>,
//		val backdrop_path: String,
//		val adult: Boolean,
//		val overview: String,
//		val release_date: String
//)