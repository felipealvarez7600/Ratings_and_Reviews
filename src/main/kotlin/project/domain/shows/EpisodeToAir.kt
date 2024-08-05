package project.domain.shows

data class EpisodeToAir(
    val id : Int,
    val name : String? = null,
    val overview : String? = null,
    val voteAverage: Double? = null,
    val voteCount : Int? = null,
    val airDate : String? = null,
    val episodeNumber : Int? = null,
    val episodeType : String? = null,
    val productionCode : String? = null,
    val runtime: Int? = null,
    val seasonNumber : Int? = null,
    val showId : Int? = null,
    val stillPath : String? = null
)