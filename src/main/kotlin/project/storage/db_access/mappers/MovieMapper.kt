package project.storage.db_access.mappers

import org.jdbi.v3.core.mapper.ColumnMapper
import org.jdbi.v3.core.statement.StatementContext
import project.domain.MovieDB
import java.sql.ResultSet
import java.sql.SQLException

class MovieMapper : ColumnMapper<MovieDB> {

    @Throws(SQLException::class)
    override fun map(rs: ResultSet?, columnNumber: Int, ctx: StatementContext?): MovieDB? {
        return if(rs != null) MovieDB(
            id = rs.getInt("movie_id"),
            title = rs.getString("title"),
            description = rs.getString("description"),
            releaseDate =  rs.getDate("release_date").toInstant(),
            duration = rs.getInt("duration").toLong(),
            rating = rs.getInt("rating")
        ) else null
    }
}