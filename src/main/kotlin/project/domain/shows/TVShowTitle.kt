package project.domain.shows

import project.domain.moviesAndShows.Genre
import project.domain.moviesAndShows.ProductionCountry

data class TVShowTitle (
    val adult : Boolean,
    val backdropPath : String,
    val genreIds : List<Int>,
    val id : Int,
    val originCountries : List<String>,
    val originalLanguage : String,
    val originalName : String,
    val overview : String,
    val popularity : Double,
    val posterPath : String,
    val firstAirDate : String,
    val name : String,
    val voteAverage : Double,
    val voteCount : Int
)