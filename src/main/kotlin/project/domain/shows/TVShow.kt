package project.domain.shows

import project.domain.moviesAndShows.Genre
import project.domain.moviesAndShows.ProductionCompany
import project.domain.moviesAndShows.ProductionCountry
import project.domain.moviesAndShows.SpokenLanguage

data class TVShow(
    val adult: Boolean,
    val backdropPath : String,
    val createdBy : List<CreatedBy>,
    val episodeRunTime : List<Int>,
    val firstAirDate : String,
    val genres : List<Genre>,
    val homepage : String,
    val id : Int,
    val inProduction : Boolean,
    val languages : List<String>,
    val lastAirDate : String,
    val lastEpisodeToAir : EpisodeToAir?,
    val name : String,
    val nextEpisodeToAir : EpisodeToAir?,
    val networks : List<Network>,
    val numberOfEpisodes : Int,
    val numberOfSeasons : Int,
    val originCountry : List<String>,
    val originalLanguage : String,
    val originalName : String,
    val overview : String,
    val popularity : Double,
    val posterPath : String,
    val productionCompanies : List<ProductionCompany>,
    val productionCountries : List<ProductionCountry>,
    val seasons : List<Season>,
    val spokenLanguages : List<SpokenLanguage>,
    val status : String,
    val tagline : String,
    val type : String,
    val voteAverage : Double,
    val voteCount : Int,
)