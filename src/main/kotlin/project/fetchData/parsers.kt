package project.fetchData

import org.json.JSONObject
import project.domain.movies.Movie
import project.domain.movies.TitleMovie
import project.domain.moviesAndShows.Genre
import project.domain.moviesAndShows.ProductionCompany
import project.domain.moviesAndShows.ProductionCountry
import project.domain.moviesAndShows.SpokenLanguage
import project.domain.shows.*

fun movieByIdParser(jsonObject: JSONObject): Movie {
    val originalLanguage = jsonObject.getString("original_language")
    val imdbId = jsonObject.getString("imdb_id")
    val video = jsonObject.getBoolean("video")
    val title = jsonObject.getString("title")
    val backdropPath = jsonObject.getString("backdrop_path")
    val revenue = jsonObject.getLong("revenue")
    val genres = jsonObject.getJSONArray("genres").map { genreParser(it as JSONObject) }
    val popularity = jsonObject.getDouble("popularity")
    val productionCountries = jsonObject.getJSONArray("production_countries").map { productionCountryParser(it as JSONObject) }
    val id = jsonObject.getInt("id")
    val voteCount = jsonObject.getInt("vote_count")
    val budget = jsonObject.getLong("budget")
    val overview = jsonObject.getString("overview")
    val originalTitle = jsonObject.getString("original_title")
    val runtime = jsonObject.getInt("runtime")
    val posterPath = jsonObject.getString("poster_path")
    val spokenLanguages = jsonObject.getJSONArray("spoken_languages").map { spokenLanguageParser(it as JSONObject) }
    val productionCompanies = jsonObject.getJSONArray("production_companies").map { productionCompanyParser(it as JSONObject) }
    val releaseDate = jsonObject.getString("release_date")
    val voteAverage = jsonObject.getDouble("vote_average")
    val belongsToCollection = jsonObject["belongs_to_collection"] as? String ?: ""
    val tagline = jsonObject.getString("tagline")
    val adult = jsonObject.getBoolean("adult")
    val homepage = jsonObject.getString("homepage")
    val status = jsonObject.getString("status")
    return Movie(originalLanguage, imdbId, video, title, backdropPath, revenue, genres,
        popularity, productionCountries, id, voteCount, budget, overview, originalTitle, runtime,
        posterPath, spokenLanguages, productionCompanies, releaseDate, voteAverage, belongsToCollection,
        tagline, adult, homepage, status)
}

fun genreParser(jsonObject: JSONObject): Genre {
    val name = jsonObject.getString("name")
    val id = jsonObject.getInt("id")
    return Genre(name, id)
}

fun productionCountryParser(jsonObject: JSONObject): ProductionCountry {
    val iso31661 = jsonObject.getString("iso_3166_1")
    val name = jsonObject.getString("name")
    return ProductionCountry(iso31661, name)
}

fun spokenLanguageParser(jsonObject: JSONObject): SpokenLanguage {
    val englishName = jsonObject.getString("english_name")
    val iso6391 = jsonObject.getString("iso_639_1")
    val name = jsonObject.getString("name")
    return SpokenLanguage(englishName, iso6391, name)
}

fun productionCompanyParser(jsonObject: JSONObject): ProductionCompany {
    val logoPath = jsonObject["logo_path"] as? String ?: ""
    val name = jsonObject.getString("name")
    val id = jsonObject.getInt("id")
    val originCountry = jsonObject.getString("origin_country")
    return ProductionCompany(logoPath, name, id, originCountry)
}

fun movieByTittleParser(jsonObject: JSONObject): TitleMovie {
    val adult = jsonObject.getBoolean("adult")
    val backdropPath = jsonObject["backdrop_path"] as? String ?: ""
    val genreIds = jsonObject.getJSONArray("genre_ids").map { it as Int }
    val id = jsonObject.getInt("id")
    val originalLanguage = jsonObject.getString("original_language")
    val originalTitle = jsonObject.getString("original_title")
    val overview = jsonObject.getString("overview")
    val popularity = jsonObject.getDouble("popularity")
    val posterPath = jsonObject["poster_path"] as? String ?: ""
    val releaseDate = jsonObject.getString("release_date")
    val title = jsonObject.getString("title")
    val video = jsonObject.getBoolean("video")
    val voteAverage = jsonObject.getDouble("vote_average")
    val voteCount = jsonObject.getInt("vote_count")
    return TitleMovie(adult, backdropPath, genreIds, id, originalLanguage,
        originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount)
}

fun tvShowByIdParser(jsonObject: JSONObject): TVShow {
    val adult = jsonObject.getBoolean("adult")
    val backdropPath = jsonObject.getString("backdrop_path")
    val createdBy = jsonObject.getJSONArray("created_by").map { createdByParser(it as JSONObject) }
    val episodeRunTime = jsonObject.getJSONArray("episode_run_time").map { it as Int }
    val firstAirDate = jsonObject.getString("first_air_date")
    val genres = jsonObject.getJSONArray("genres").map { genreParser(it as JSONObject) }
    val homepage = jsonObject.getString("homepage")
    val id = jsonObject.getInt("id")
    val inProduction = jsonObject.getBoolean("in_production")
    val languages = jsonObject.getJSONArray("languages").map { it as String }
    val lastAirDate = jsonObject.getString("last_air_date")
    val lastEpisodeToAir = (jsonObject["last_episode_to_air"] as? JSONObject)?.let { episodeToAirParser(it) }
    val name = jsonObject.getString("name")
    val nextEpisodeToAir = (jsonObject["next_episode_to_air"] as? JSONObject)?.let { episodeToAirParser(it) }
    val networks = jsonObject.getJSONArray("networks").map { networkParser(it as JSONObject) }
    val numberOfEpisodes = jsonObject.getInt("number_of_episodes")
    val numberOfSeasons = jsonObject.getInt("number_of_seasons")
    val originCountry = jsonObject.getJSONArray("origin_country").map { it as String }
    val originalLanguage = jsonObject.getString("original_language")
    val originalName = jsonObject.getString("original_name")
    val overview = jsonObject.getString("overview")
    val popularity = jsonObject.getDouble("popularity")
    val posterPath = jsonObject.getString("poster_path")
    val productionCompanies = jsonObject.getJSONArray("production_companies").map { productionCompanyParser(it as JSONObject) }
    val productionCountries = jsonObject.getJSONArray("production_countries").map { productionCountryParser(it as JSONObject) }
    val seasons = jsonObject.getJSONArray("seasons").map { seasonParser(it as JSONObject) }
    val spokenLanguages = jsonObject.getJSONArray("spoken_languages").map { spokenLanguageParser(it as JSONObject) }
    val status = jsonObject.getString("status")
    val tagline = jsonObject.getString("tagline")
    val type = jsonObject.getString("type")
    val voteAverage = jsonObject.getDouble("vote_average")
    val voteCount = jsonObject.getInt("vote_count")
    return TVShow(adult, backdropPath, createdBy, episodeRunTime, firstAirDate, genres,
        homepage, id, inProduction, languages, lastAirDate, lastEpisodeToAir, name, nextEpisodeToAir,
        networks, numberOfEpisodes, numberOfSeasons, originCountry, originalLanguage, originalName,
        overview, popularity, posterPath, productionCompanies, productionCountries, seasons,
        spokenLanguages, status, tagline, type, voteAverage, voteCount)
}

fun createdByParser(jsonObject: JSONObject): CreatedBy {
    val id = jsonObject.getInt("id")
    val creditId = jsonObject.getString("credit_id")
    val name = jsonObject.getString("name")
    return CreatedBy(id, creditId, name)
}

fun episodeToAirParser(jsonObject: JSONObject): EpisodeToAir {
    val id = jsonObject.getInt("id")
    val name = jsonObject.getString("name")
    val overview = jsonObject.getString("overview")
    val voteAverage = jsonObject.getDouble("vote_average")
    val voteCount = jsonObject.getInt("vote_count")
    val airDate = jsonObject.getString("air_date")
    val episodeNumber = jsonObject.getInt("episode_number")
    val episodeType = jsonObject.getString("episode_type")
    val productionCode = jsonObject.getString("production_code")
    val runtime = jsonObject.getInt("runtime")
    val seasonNumber = jsonObject.getInt("season_number")
    val showId = jsonObject.getInt("show_id")
    val stillPath = jsonObject["still_path"] as? String ?: ""
    return EpisodeToAir(id, name, overview, voteAverage, voteCount, airDate, episodeNumber,
        episodeType, productionCode, runtime, seasonNumber, showId, stillPath)
}

fun networkParser(jsonObject: JSONObject): Network {
    val id = jsonObject.getInt("id")
    val logoPath = jsonObject.getString("logo_path")
    val name = jsonObject.getString("name")
    val originCountry = jsonObject.getString("origin_country")
    return Network(id, logoPath, name, originCountry)
}

fun seasonParser(jsonObject: JSONObject): Season {
    val airDate = jsonObject["air_date"] as? String ?: ""
    val episodeCount = jsonObject.getInt("episode_count")
    val id = jsonObject.getInt("id")
    val name = jsonObject.getString("name")
    val overview = jsonObject.getString("overview")
    val posterPath = jsonObject["poster_path"] as? String ?: ""
    val seasonNumber = jsonObject.getInt("season_number")
    val voteAverage = jsonObject.getDouble("vote_average")
    return Season(airDate, episodeCount, id, name, overview, posterPath, seasonNumber, voteAverage)
}