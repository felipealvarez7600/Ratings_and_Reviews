package project.storage.db_access.mappers

import org.jdbi.v3.core.mapper.ColumnMapper
import org.jdbi.v3.core.statement.StatementContext
import project.domain.movies.Movie
import java.sql.ResultSet
import java.sql.SQLException

class MovieMapper : ColumnMapper<Movie> {

    @Throws(SQLException::class)
    override fun map(rs: ResultSet?, columnNumber: Int, ctx: StatementContext?): Movie? {
        return if(rs != null) Movie(
            id = rs.getInt("id"),
            title = rs.getString("title"),
            originalLanguage = rs.getString("original_language"),
            imdbId = rs.getString("imdb_id"),
            video = rs.getBoolean("video"),
            backdropPath = rs.getString("backdrop_path"),
            revenue = rs.getLong("revenue"),
            popularity = rs.getDouble("popularity"),
            voteCount = rs.getInt("vote_count"),
            budget = rs.getLong("budget"),
            overview = rs.getString("overview"),
            originalTitle = rs.getString("original_title"),
            runtime = rs.getInt("runtime"),
            posterPath = rs.getString("poster_path"),
            releaseDate = rs.getString("release_date"),
            voteAverage = rs.getDouble("vote_average"),
            belongsToCollection = rs.getString("belongs_to_collection"),
            tagline = rs.getString("tagline"),
            adult = rs.getBoolean("adult"),
            homepage = rs.getString("homepage"),
            status = rs.getString("status"),
            genres = emptyList(),
            productionCompanies = emptyList(),
            productionCountries = emptyList(),
            spokenLanguages = emptyList()
        ) else null
    }
}