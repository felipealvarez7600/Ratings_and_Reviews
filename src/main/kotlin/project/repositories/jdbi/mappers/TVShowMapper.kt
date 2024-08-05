package project.repositories.jdbi.mappers

import org.jdbi.v3.core.mapper.ColumnMapper
import org.jdbi.v3.core.statement.StatementContext
import project.domain.shows.EpisodeToAir
import project.domain.shows.TVShow
import java.sql.ResultSet

class TVShowMapper : ColumnMapper<TVShow> {

        override fun map(rs: ResultSet?, columnNumber: Int, ctx: StatementContext?): TVShow? {
            return if (rs != null) {
                TVShow(
                    adult = rs.getBoolean("adult"),
                    backdropPath = rs.getString("backdrop_path"),
                    episodeRunTime = rs.getArray("episode_run_time").array as List<Int>,
                    firstAirDate = rs.getString("first_air_date"),
                    homepage = rs.getString("homepage"),
                    id = rs.getInt("id"),
                    inProduction = rs.getBoolean("in_production"),
                    languages = rs.getArray("languages").array as List<String>,
                    lastAirDate = rs.getString("last_air_date"),
                    lastEpisodeToAir = EpisodeToAir(rs.getInt("last_episode_to_air")),
                    name = rs.getString("name"),
                    nextEpisodeToAir = EpisodeToAir(rs.getInt("next_episode_to_air")),
                    numberOfEpisodes = rs.getInt("number_of_episodes"),
                    numberOfSeasons = rs.getInt("number_of_seasons"),
                    originCountry = rs.getArray("origin_country").array as List<String>,
                    originalLanguage = rs.getString("original_language"),
                    originalName = rs.getString("original_name"),
                    overview = rs.getString("overview"),
                    popularity = rs.getDouble("popularity"),
                    posterPath = rs.getString("poster_path"),
                    status = rs.getString("status"),
                    tagline = rs.getString("tagline"),
                    type = rs.getString("type"),
                    voteAverage = rs.getDouble("vote_average"),
                    voteCount = rs.getInt("vote_count"),
                    createdBy = emptyList(),
                    genres = emptyList(),
                    networks = emptyList(),
                    productionCompanies = emptyList(),
                    productionCountries = emptyList(),
                    seasons = emptyList(),
                    spokenLanguages = emptyList()
                )
            } else null
        }
}