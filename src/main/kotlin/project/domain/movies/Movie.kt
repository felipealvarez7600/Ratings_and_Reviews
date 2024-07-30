package project.domain.movies

import project.domain.moviesAndShows.Genre
import project.domain.moviesAndShows.ProductionCompany
import project.domain.moviesAndShows.ProductionCountry
import project.domain.moviesAndShows.SpokenLanguage

data class Movie(
    val originalLanguage : String,
    val imdbId : String,
    val video : Boolean,
    val title : String,
    val backdropPath : String,
    val revenue : Long,
    val genres : List<Genre>,
    val popularity : Double,
    val productionCountries : List<ProductionCountry>,
    val id : Int,
    val voteCount : Int,
    val budget : Long,
    val overview : String,
    val originalTitle : String,
    val runtime : Int,
    val posterPath : String,
    val spokenLanguages : List<SpokenLanguage>,
    val productionCompanies : List<ProductionCompany>,
    val releaseDate : String,
    val voteAverage : Double,
    val belongsToCollection : String,
    val tagline : String,
    val adult : Boolean,
    val homepage : String,
    val status : String,
)