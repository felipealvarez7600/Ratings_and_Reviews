package project.repositories.jdbi.mappers

import org.jdbi.v3.core.mapper.ColumnMapper
import org.jdbi.v3.core.statement.StatementContext
import project.domain.movies.Movie
import project.domain.movies.TitleMovie
import java.sql.ResultSet
import java.sql.SQLException

class TitleMovieMapper : ColumnMapper<TitleMovie> {

    @Throws(SQLException::class)
    override fun map(rs: ResultSet?, columnNumber: Int, ctx: StatementContext?): TitleMovie? {
        return if(rs != null) TitleMovie(
            id = rs.getInt("id"),
            title = rs.getString("title"),
            originalLanguage = rs.getString("original_language"),
            video = rs.getBoolean("video"),
            backdropPath = rs.getString("backdrop_path"),
            popularity = rs.getDouble("popularity"),
            voteCount = rs.getInt("vote_count"),
            overview = rs.getString("overview"),
            originalTitle = rs.getString("original_title"),
            posterPath = rs.getString("poster_path"),
            releaseDate = rs.getString("release_date"),
            voteAverage = rs.getDouble("vote_average"),
            adult = rs.getBoolean("adult"),
            genreIds = emptyList()
        ) else null
    }
}